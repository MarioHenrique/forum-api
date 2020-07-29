package com.forumapi.features.questions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionNotFoundException extends ResponseStatusException {

    private static final String QUESTION_NOT_FOUND = "Questão não encontrada";

    public QuestionNotFoundException(){
        super(HttpStatus.NOT_FOUND, QUESTION_NOT_FOUND);
    }

}
