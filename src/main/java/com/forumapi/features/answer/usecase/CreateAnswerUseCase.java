package com.forumapi.features.answer.usecase;

import com.forumapi.entities.Answer;
import com.forumapi.features.answer.business.AnswerCreator;
import com.forumapi.features.answer.infra.AnswerRepository;
import com.forumapi.features.answer.model.AnswerTO;
import com.forumapi.features.answer.model.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateAnswerUseCase {

    private final AnswerCreator answerCreator;
    private final AnswerRepository answerRepository;

    @Autowired
    public CreateAnswerUseCase(AnswerCreator answerCreator, AnswerRepository answerRepository) {
        this.answerCreator = answerCreator;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public AnswerVO create(AnswerTO answerTO){
        Answer answer = answerCreator.create(answerTO);
        answerRepository.save(answer);
        return new AnswerVO(answer);
    }

}
