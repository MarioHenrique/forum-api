package com.forumapi.features.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyRegistered extends ResponseStatusException {

    private static final String USER_ALREADY_REGISTERED = "Usuário já cadastrado";

    public UserAlreadyRegistered(){
        super(HttpStatus.BAD_REQUEST, USER_ALREADY_REGISTERED);
    }

}
