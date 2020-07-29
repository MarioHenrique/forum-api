package com.forumapi.features.votes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VoteNotFoundException extends ResponseStatusException {

    public static final String VOTE_NOT_FOUND = "Voto não encontrado";

    public VoteNotFoundException() {
        super(HttpStatus.NOT_FOUND, VOTE_NOT_FOUND);
    }

}
