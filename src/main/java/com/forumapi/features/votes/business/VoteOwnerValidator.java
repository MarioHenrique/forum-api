package com.forumapi.features.votes.business;

import com.forumapi.entities.User;
import com.forumapi.entities.Vote;
import com.forumapi.features.votes.exceptions.VoteNotFoundException;
import com.forumapi.features.votes.exceptions.VoteUpdateUnauthorizedException;
import com.forumapi.features.votes.infra.VoteRepository;
import com.forumapi.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteOwnerValidator {

    private final VoteRepository voteRepository;
    private final PrincipalService principalService;

    @Autowired
    public VoteOwnerValidator(VoteRepository voteRepository, PrincipalService principalService) {
        this.voteRepository = voteRepository;
        this.principalService = principalService;
    }

    public void validate(Long voteId){
        Vote vote = voteRepository.findById(voteId).orElseThrow(VoteNotFoundException::new);
        User user = principalService.getUserAuthenticated();
        if(!vote.isOwner(user)){
            throw new VoteUpdateUnauthorizedException();
        }
    }

}
