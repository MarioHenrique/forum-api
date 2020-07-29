package com.forumapi.events.question.listeners;

import com.forumapi.documents.QuestionDocument;
import com.forumapi.events.question.source.DeleteQuestionEventSource;
import com.forumapi.events.question.source.DeleteQuestionEventTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DeleteQuestionListener {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DeleteQuestionListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @TransactionalEventListener
    @Async
    public void deleteQuestion(DeleteQuestionEventSource question){
        DeleteQuestionEventTO deleteQuestionEventTO = (DeleteQuestionEventTO)question.getSource();
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(deleteQuestionEventTO.getUserId())), new Update().pull("questions", new QuestionDocument(deleteQuestionEventTO.getQuestionId())), "userDocument");
    }

}
