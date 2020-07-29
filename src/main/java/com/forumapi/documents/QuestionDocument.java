package com.forumapi.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class QuestionDocument {

    @Id
    private Long id;
    private String description;

    protected QuestionDocument(){}

    public QuestionDocument(Long id) {
        this.id = id;
    }

    public QuestionDocument(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
