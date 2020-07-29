package com.forumapi.events.user.source;

import org.springframework.context.ApplicationEvent;

public class UserEventSource extends ApplicationEvent {

    public UserEventSource(UserEventTO source) {
        super(source);
    }

}
