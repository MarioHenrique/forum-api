package com.forumapi.events.question.listeners;

import com.forumapi.documents.QuestionDocument;
import com.forumapi.events.question.source.NewQuestionEventSource;
import com.forumapi.events.question.source.QuestionEventTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NewQuestionListener {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NewQuestionListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @TransactionalEventListener
    @Async
    public void newQuestion(NewQuestionEventSource question){
        QuestionEventTO questionEventTO = (QuestionEventTO)question.getSource();
        QuestionDocument questionDocument = new QuestionDocument(questionEventTO.getQuestionId(), questionEventTO.getDescription());
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(questionEventTO.getUserId())), new Update().push("questions", questionDocument), "userDocument");
    }

}
