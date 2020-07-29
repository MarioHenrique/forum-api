package com.forumapi.events.question.source;

import org.springframework.context.ApplicationEvent;

public class UpdateQuestionEventSource extends ApplicationEvent {

    public UpdateQuestionEventSource(QuestionEventTO source) {
        super(source);
    }

}
