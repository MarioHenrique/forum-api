package com.forumapi.features.questions.usecase;

import com.forumapi.entities.Question;
import com.forumapi.features.questions.business.QuestionUpdateValidator;
import com.forumapi.features.questions.business.QuestionUpdater;
import com.forumapi.features.questions.business.UpdateQuestionCacheEventPublisher;
import com.forumapi.features.questions.exceptions.QuestionNotFoundException;
import com.forumapi.features.questions.infra.QuestionRepository;
import com.forumapi.features.questions.model.QuestionTO;
import com.forumapi.features.questions.model.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpdateQuestionUseCase {

    private final UpdateQuestionCacheEventPublisher updateQuestionCacheEventPublisher;
    private final QuestionUpdateValidator questionUpdateValidator;
    private final QuestionRepository questionRepository;
    private final QuestionUpdater questionUpdater;

    @Autowired
    public UpdateQuestionUseCase(UpdateQuestionCacheEventPublisher updateQuestionCacheEventPublisher,
                                 QuestionUpdateValidator questionUpdateValidator,
                                 QuestionRepository questionRepository,
                                 QuestionUpdater questionUpdater) {
        this.updateQuestionCacheEventPublisher = updateQuestionCacheEventPublisher;
        this.questionUpdateValidator = questionUpdateValidator;
        this.questionRepository = questionRepository;
        this.questionUpdater = questionUpdater;
    }

    @Transactional
    public QuestionVO update(Long id, QuestionTO questionTO){
        questionUpdateValidator.validate(id, questionTO);
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
        questionUpdater.update(question, questionTO);
        questionRepository.save(question);
        updateQuestionCacheEventPublisher.onUpdateQuestion(question);
        return new QuestionVO(question);
    }

}
