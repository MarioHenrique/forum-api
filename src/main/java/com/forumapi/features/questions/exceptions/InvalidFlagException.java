package com.forumapi.features.questions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class InvalidFlagException extends ResponseStatusException {

    private static final String INVALID_FLAGS = "As seguintes flags são invalidas %s";

    public InvalidFlagException(List<Long> invalidFlags) {
        super(HttpStatus.BAD_REQUEST, String.format(INVALID_FLAGS, invalidFlags));
    }

}
