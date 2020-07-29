package com.forumapi.features.questions.usecase;

import com.forumapi.entities.Question;
import com.forumapi.features.questions.business.DeleteQuestionCacheEventPublisher;
import com.forumapi.features.questions.business.QuestionOwnerValidator;
import com.forumapi.features.questions.business.UserVoteAnswerQuestionReputation;
import com.forumapi.features.questions.exceptions.QuestionNotFoundException;
import com.forumapi.features.questions.infra.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteQuestionUseCase {

    private final QuestionRepository questionRepository;
    private final QuestionOwnerValidator questionOwnerValidator;
    private final UserVoteAnswerQuestionReputation userVoteAnswerQuestionReputation;
    private final DeleteQuestionCacheEventPublisher deleteQuestionCacheEventPublisher;

    @Autowired
    public DeleteQuestionUseCase(QuestionRepository questionRepository,
                                 QuestionOwnerValidator questionOwnerValidator,
                                 UserVoteAnswerQuestionReputation userVoteAnswerQuestionReputation,
                                 DeleteQuestionCacheEventPublisher deleteQuestionCacheEventPublisher) {
        this.questionRepository = questionRepository;
        this.questionOwnerValidator = questionOwnerValidator;
        this.userVoteAnswerQuestionReputation = userVoteAnswerQuestionReputation;
        this.deleteQuestionCacheEventPublisher = deleteQuestionCacheEventPublisher;
    }

    @Transactional
    public void delete(Long questionId){
        questionOwnerValidator.validate(questionId);
        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
        questionRepository.delete(question);
        userVoteAnswerQuestionReputation.onDeleteQuestion(question.getId());
        deleteQuestionCacheEventPublisher.onDeleteQuestion(question);
    }

}
