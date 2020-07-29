package com.forumapi.features.answer.usecase;

import com.forumapi.features.answer.business.AnswerOwnerValidator;
import com.forumapi.features.answer.business.UserVoteAnswerReputation;
import com.forumapi.features.answer.infra.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteAnswerUseCase {

    private final AnswerOwnerValidator answerOwnerValidator;
    private final AnswerRepository answerRepository;
    private final UserVoteAnswerReputation userVoteAnswerReputation;

    @Autowired
    public DeleteAnswerUseCase(AnswerOwnerValidator answerOwnerValidator,
                               AnswerRepository answerRepository,
                               UserVoteAnswerReputation userVoteAnswerReputation) {
        this.answerOwnerValidator = answerOwnerValidator;
        this.answerRepository = answerRepository;
        this.userVoteAnswerReputation = userVoteAnswerReputation;
    }

    @Transactional
    public void delete(Long answerId){
        answerOwnerValidator.validate(answerId);
        answerRepository.deleteById(answerId);
        userVoteAnswerReputation.onDeleteAnswer(answerId);
    }

}
