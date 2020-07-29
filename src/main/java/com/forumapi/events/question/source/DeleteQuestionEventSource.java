package com.forumapi.events.question.source;

import org.springframework.context.ApplicationEvent;

public class DeleteQuestionEventSource extends ApplicationEvent {

    public DeleteQuestionEventSource(DeleteQuestionEventTO question) {
        super(question);
    }

}
