package com.forumapi.features.answer.model;

import com.forumapi.entities.Answer;
import lombok.Getter;

@Getter
public class AnswerVO {

    private final Long id;
    private final Long questionId;
    private final String coments;

    public AnswerVO(Answer answer){
        this.id = answer.getId();
        this.questionId = answer.getQuestionId();
        this.coments = answer.getComents();
    }


}
