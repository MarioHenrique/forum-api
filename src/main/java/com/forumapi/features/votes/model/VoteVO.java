package com.forumapi.features.votes.model;

import com.forumapi.entities.Vote;
import lombok.Getter;

@Getter
public class VoteVO {

    private final Long id;
    private final Integer score;

    public VoteVO(Vote vote){
        this.id = vote.getId();
        this.score = vote.getScore();
    }

}
