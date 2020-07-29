package com.forumapi.features.answer.usecase;

import com.forumapi.entities.Answer;
import com.forumapi.features.answer.business.AnswerOwnerValidator;
import com.forumapi.features.answer.exceptions.AnswerNotFoundException;
import com.forumapi.features.answer.infra.AnswerRepository;
import com.forumapi.features.answer.model.AnswerUpdateTO;
import com.forumapi.features.answer.model.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAnswerUseCase {

    private final AnswerRepository answerRepository;
    private final AnswerOwnerValidator answerOwnerValidator;

    @Autowired
    public UpdateAnswerUseCase(AnswerRepository answerRepository,
                               AnswerOwnerValidator answerOwnerValidator) {
        this.answerRepository = answerRepository;
        this.answerOwnerValidator = answerOwnerValidator;
    }


    public AnswerVO update(Long id, AnswerUpdateTO answerTO){
        answerOwnerValidator.validate(id);
        Answer answer = answerRepository.findById(id).orElseThrow(AnswerNotFoundException::new);
        answer.setComents(answerTO.getComents());
        answerRepository.save(answer);
        return new AnswerVO(answer);
    }

}
