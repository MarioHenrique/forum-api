package com.forumapi.features.votes.business;

import com.forumapi.entities.Vote;
import com.forumapi.events.reputation.source.UserReputationEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class VoteUserReputation {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public VoteUserReputation(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onChangeVote(Vote vote){
        this.applicationEventPublisher.publishEvent(new UserReputationEventSource(Arrays.asList(vote.getUserId())));
    }

}
