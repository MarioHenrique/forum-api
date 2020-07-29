package com.forumapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthenticatedUserNotFound extends RuntimeException {

    public static final String AUTHENTICATED_USER_NOT_FOUND = "Não foi encontrado o usuario autenticado";

    public AuthenticatedUserNotFound() {
        super(AUTHENTICATED_USER_NOT_FOUND);
    }

}
