package com.forumapi.features.users.business;

import com.forumapi.entities.User;
import com.forumapi.events.user.source.UserEventSource;
import com.forumapi.events.user.source.UserEventTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserCacheEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserCacheEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onCreateUser(User user){
        UserEventTO userEventTO = new UserEventTO(user.getId(), user.getName(), user.getEmail());
        applicationEventPublisher.publishEvent(new UserEventSource(userEventTO));
    }

}
