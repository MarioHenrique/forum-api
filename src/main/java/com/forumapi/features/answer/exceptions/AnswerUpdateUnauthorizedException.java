package com.forumapi.features.answer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AnswerUpdateUnauthorizedException extends ResponseStatusException {

    private static final String UPDATE_NOT_ALLOWED = "Apenas o usuário criador da resposta pode modifica-la";

    public AnswerUpdateUnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, UPDATE_NOT_ALLOWED);
    }
}
