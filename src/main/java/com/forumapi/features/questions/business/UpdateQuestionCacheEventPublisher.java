package com.forumapi.features.questions.business;

import com.forumapi.entities.Question;
import com.forumapi.events.question.source.QuestionEventTO;
import com.forumapi.events.question.source.UpdateQuestionEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UpdateQuestionCacheEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UpdateQuestionCacheEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onUpdateQuestion(Question question){
        QuestionEventTO questionEventTO = new QuestionEventTO(question.getUserId(), question.getId(), question.getDescription());
        applicationEventPublisher.publishEvent(new UpdateQuestionEventSource(questionEventTO));
    }

}
