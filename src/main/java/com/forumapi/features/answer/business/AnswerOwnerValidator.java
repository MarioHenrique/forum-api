package com.forumapi.features.answer.business;

import com.forumapi.entities.Answer;
import com.forumapi.entities.User;
import com.forumapi.features.answer.exceptions.AnswerNotFoundException;
import com.forumapi.features.answer.exceptions.AnswerUpdateUnauthorizedException;
import com.forumapi.features.answer.infra.AnswerRepository;
import com.forumapi.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerOwnerValidator {

    private final AnswerRepository answerRepository;
    private final PrincipalService principalService;

    @Autowired
    public AnswerOwnerValidator(AnswerRepository answerRepository, PrincipalService principalService) {
        this.answerRepository = answerRepository;
        this.principalService = principalService;
    }

    public void validate(Long answerId){
        Answer answer = answerRepository.findById(answerId).orElseThrow(AnswerNotFoundException::new);
        User user = principalService.getUserAuthenticated();
        if(!answer.isOwner(user)){
            throw new AnswerUpdateUnauthorizedException();
        }
    }

}
