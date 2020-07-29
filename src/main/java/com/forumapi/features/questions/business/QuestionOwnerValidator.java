package com.forumapi.features.questions.business;

import com.forumapi.entities.Question;
import com.forumapi.entities.User;
import com.forumapi.features.questions.exceptions.QuestionNotFoundException;
import com.forumapi.features.questions.exceptions.QuestionUpdateUnauthorizedException;
import com.forumapi.features.questions.infra.QuestionRepository;
import com.forumapi.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionOwnerValidator {

    private final QuestionRepository questionRepository;
    private final PrincipalService principalService;

    @Autowired
    public QuestionOwnerValidator(QuestionRepository questionRepository,
                                  PrincipalService principalService) {
        this.questionRepository = questionRepository;
        this.principalService = principalService;
    }

    public void validate(Long questionId){
        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
        User userAuthenticated = principalService.getUserAuthenticated();
        if(!question.isOwner(userAuthenticated)){
            throw new QuestionUpdateUnauthorizedException();
        }
    }

}
