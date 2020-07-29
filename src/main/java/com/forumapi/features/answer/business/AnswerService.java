package com.forumapi.features.answer.business;

import com.forumapi.entities.Answer;
import com.forumapi.features.answer.exceptions.AnswerNotFoundException;
import com.forumapi.features.answer.infra.AnswerRepository;
import com.forumapi.features.answer.model.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public AnswerVO searchAnswer(Long id){
        Answer answer = this.answerRepository.findById(id).orElseThrow(AnswerNotFoundException::new);
        return new AnswerVO(answer);
    }

}
