package com.forumapi.features.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserUpdateUnauthorizedException extends ResponseStatusException {

    private static final String UPDATE_OWN_USER = "É permitido alterar apenas o proprio usuario";

    public UserUpdateUnauthorizedException(){
        super(HttpStatus.UNAUTHORIZED, UPDATE_OWN_USER);
    }

}
