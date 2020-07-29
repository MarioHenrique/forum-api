package com.forumapi.features.votes.usecase;

import com.forumapi.entities.Vote;
import com.forumapi.features.votes.business.VoteUserReputation;
import com.forumapi.features.votes.exceptions.VoteNotFoundException;
import com.forumapi.features.votes.infra.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteVoteUserCase {

    private final VoteRepository voteRepository;
    private final VoteUserReputation voteUserReputation;

    @Autowired
    public DeleteVoteUserCase(VoteRepository voteRepository, VoteUserReputation voteUserReputation) {
        this.voteRepository = voteRepository;
        this.voteUserReputation = voteUserReputation;
    }

    @Transactional
    public void delete(Long voteId){
        Vote vote = voteRepository.findById(voteId).orElseThrow(VoteNotFoundException::new);
        voteRepository.delete(vote);
        voteUserReputation.onChangeVote(vote);
    }

}
