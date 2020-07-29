package com.forumapi.features.questions.usecase;

import com.forumapi.entities.Question;
import com.forumapi.features.questions.business.NewQuestionCacheEventPublisher;
import com.forumapi.features.questions.business.QuestionCreator;
import com.forumapi.features.questions.business.QuestionCreateValidator;
import com.forumapi.features.questions.infra.QuestionRepository;
import com.forumapi.features.questions.model.QuestionTO;
import com.forumapi.features.questions.model.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateQuestionUseCase {

    private final QuestionCreateValidator questionCreateValidator;
    private final QuestionCreator questionCreator;
    private final QuestionRepository questionRepository;
    private final NewQuestionCacheEventPublisher newQuestionCacheEventPublisher;

    @Autowired
    public CreateQuestionUseCase(QuestionCreateValidator questionCreateValidator,
                                 QuestionCreator questionCreator,
                                 QuestionRepository questionRepository,
                                 NewQuestionCacheEventPublisher newQuestionCacheEventPublisher) {
        this.questionCreateValidator = questionCreateValidator;
        this.questionCreator = questionCreator;
        this.questionRepository = questionRepository;
        this.newQuestionCacheEventPublisher = newQuestionCacheEventPublisher;
    }

    @Transactional
    public QuestionVO create(QuestionTO questionTO){
        questionCreateValidator.validate(questionTO);
        Question question = questionCreator.create(questionTO);
        questionRepository.save(question);
        newQuestionCacheEventPublisher.onCreateQuestion(question);
        return new QuestionVO(question);
    }

}
