package com.forumapi.features.votes.usecase;

import com.forumapi.entities.Vote;
import com.forumapi.features.votes.business.VoteOwnerValidator;
import com.forumapi.features.votes.business.VoteUserReputation;
import com.forumapi.features.votes.exceptions.VoteNotFoundException;
import com.forumapi.features.votes.infra.VoteRepository;
import com.forumapi.features.votes.model.VoteUpdateTO;
import com.forumapi.features.votes.model.VoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpdateVoteUseCase {

    private final VoteRepository voteRepository;
    private final VoteOwnerValidator voteOwnerValidator;
    private final VoteUserReputation voteUserReputation;

    @Autowired
    public UpdateVoteUseCase(VoteRepository voteRepository,
                             VoteOwnerValidator voteOwnerValidator,
                             VoteUserReputation voteUserReputation) {
        this.voteRepository = voteRepository;
        this.voteOwnerValidator = voteOwnerValidator;
        this.voteUserReputation = voteUserReputation;
    }

    @Transactional
    public VoteVO update(Long id, VoteUpdateTO voteUpdateTO) {
        voteOwnerValidator.validate(id);
        Vote vote = voteRepository.findById(id).orElseThrow(VoteNotFoundException::new);
        vote.setScore(voteUpdateTO.getScore());
        voteRepository.save(vote);
        voteUserReputation.onChangeVote(vote);
        return new VoteVO(vote);
    }

}
