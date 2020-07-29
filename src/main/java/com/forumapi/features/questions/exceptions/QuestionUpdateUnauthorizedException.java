package com.forumapi.features.questions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionUpdateUnauthorizedException extends ResponseStatusException {

    private static final String UPDATE_QUESTION_UNAUTHORIZED = "Apenas o usuário criador da questão pode modifica-la";

    public QuestionUpdateUnauthorizedException(){
        super(HttpStatus.UNAUTHORIZED, UPDATE_QUESTION_UNAUTHORIZED);
    }

}
