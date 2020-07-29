package com.forumapi.events.question.listeners;

import com.forumapi.documents.QuestionDocument;
import com.forumapi.events.question.source.QuestionEventTO;
import com.forumapi.events.question.source.UpdateQuestionEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UpdateQuestionListener {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UpdateQuestionListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @TransactionalEventListener
    @Async
    public void onUpdateQuestion(UpdateQuestionEventSource updateQuestionEventSource){
        QuestionEventTO questionEventTO = (QuestionEventTO)updateQuestionEventSource.getSource();
        QuestionDocument questionDocument = new QuestionDocument(questionEventTO.getQuestionId(), questionEventTO.getDescription());
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(questionEventTO.getUserId())),
                new Update().set("questions.$[element]", questionDocument)
                        .filterArray("element._id", questionEventTO.getQuestionId()), "userDocument");
    }

}
