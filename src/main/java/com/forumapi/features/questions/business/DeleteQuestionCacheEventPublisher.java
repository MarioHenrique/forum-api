package com.forumapi.features.questions.business;

import com.forumapi.entities.Question;
import com.forumapi.events.question.source.DeleteQuestionEventSource;
import com.forumapi.events.question.source.DeleteQuestionEventTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DeleteQuestionCacheEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public DeleteQuestionCacheEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDeleteQuestion(Question question){
        DeleteQuestionEventTO questionEventTO = new DeleteQuestionEventTO(question.getUserId(), question.getId());
        applicationEventPublisher.publishEvent(new DeleteQuestionEventSource(questionEventTO));
    }

}
