package com.forumapi.features.votes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VoteUpdateUnauthorizedException extends ResponseStatusException {

    private static final String UPDATE_VOTE_UNAUTHORIZED = "Apenas o criador do voto é que pode modifica-lo";

    public VoteUpdateUnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, UPDATE_VOTE_UNAUTHORIZED);
    }

}
