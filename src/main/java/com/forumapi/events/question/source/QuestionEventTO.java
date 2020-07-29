package com.forumapi.events.question.source;

public class QuestionEventTO {

    private final Long userId;
    private final Long questionId;
    private final String description;

    public QuestionEventTO(Long userId,
                           Long questionId,
                           String description) {
        this.userId = userId;
        this.questionId = questionId;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getDescription() {
        return description;
    }
}
