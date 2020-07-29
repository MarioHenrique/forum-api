package com.forumapi.features.answer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AnswerNotFoundException extends ResponseStatusException {

    public static final String ANSWER_NOT_FOUND = "Resposta não encontrada";

    public AnswerNotFoundException() {
        super(HttpStatus.NOT_FOUND, ANSWER_NOT_FOUND);
    }

}
