package com.forumapi.events.question.source;

import org.springframework.beans.factory.annotation.Autowired;

public class DeleteQuestionEventTO {

    private final Long userId;
    private final Long questionId;

    @Autowired
    public DeleteQuestionEventTO(Long userId,
                                 Long questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

}
