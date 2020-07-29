package com.forumapi.features.questions.business;

import com.forumapi.entities.Question;
import com.forumapi.events.question.source.NewQuestionEventSource;
import com.forumapi.events.question.source.QuestionEventTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NewQuestionCacheEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public NewQuestionCacheEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onCreateQuestion(Question question){
        QuestionEventTO questionEventTO = new QuestionEventTO(question.getUserId(), question.getId(), question.getDescription());
        applicationEventPublisher.publishEvent(new NewQuestionEventSource(questionEventTO));
    }

}
