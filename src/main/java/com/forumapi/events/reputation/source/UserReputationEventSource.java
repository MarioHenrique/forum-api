package com.forumapi.events.reputation.source;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public class UserReputationEventSource extends ApplicationEvent {

    public UserReputationEventSource(List<Long> usersId) {
        super(usersId);
    }

}
