package com.forumapi.features.info.model;

import com.forumapi.documents.QuestionDocument;
import lombok.Getter;

@Getter
public class QuestionInfoVO {

    private final Long id;
    private final String description;

    public QuestionInfoVO(QuestionDocument questionDocument) {
        this.id = questionDocument.getId();
        this.description = questionDocument.getDescription();
    }

}
