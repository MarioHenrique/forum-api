package com.forumapi.features.votes.usecase;

import com.forumapi.entities.Vote;
import com.forumapi.features.votes.business.VoteUserReputation;
import com.forumapi.features.votes.business.VoteCreator;
import com.forumapi.features.votes.infra.VoteRepository;
import com.forumapi.features.votes.model.VoteTO;
import com.forumapi.features.votes.model.VoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CreateVoteUseCase {

    private final VoteRepository voteRepository;
    private final VoteCreator voteCreator;
    private final VoteUserReputation userReputation;

    @Autowired
    public CreateVoteUseCase(VoteRepository voteRepository,
                             VoteCreator voteCreator, VoteUserReputation userReputation) {
        this.voteRepository = voteRepository;
        this.voteCreator = voteCreator;
        this.userReputation = userReputation;
    }

    @Transactional
    public VoteVO create(VoteTO voteTO){
        Vote vote = voteCreator.create(voteTO);
        voteRepository.save(vote);
        userReputation.onChangeVote(vote);
        return new VoteVO(vote);
    }

}
