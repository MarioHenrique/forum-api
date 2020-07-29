package com.forumapi.features.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    private static final String USUÁRIO_NÃO_EXISTE = "Usuário não existe";

    public UserNotFoundException(){
        super(HttpStatus.NOT_FOUND, USUÁRIO_NÃO_EXISTE);
    }

}
