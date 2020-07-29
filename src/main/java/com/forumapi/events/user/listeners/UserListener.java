package com.forumapi.events.user.listeners;

import com.forumapi.documents.UserDocument;
import com.forumapi.events.user.source.UserEventSource;
import com.forumapi.events.user.source.UserEventTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserListener {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @TransactionalEventListener
    @Async
    public void newUser(UserEventSource userEventSource){
        UserEventTO userEventTO = (UserEventTO) userEventSource.getSource();
        UserDocument document = new UserDocument(userEventTO.getId(), userEventTO.getName(), userEventTO.getEmail());
        mongoTemplate.save(document);
    }

}
