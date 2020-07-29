package com.forumapi.events.question.source;

import org.springframework.context.ApplicationEvent;

public class NewQuestionEventSource extends ApplicationEvent {

    public NewQuestionEventSource(QuestionEventTO source) {
        super(source);
    }

}
