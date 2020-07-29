package com.forumapi.documents;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.util.List;

@Document
@Getter
public class UserDocument {

    @Id
    private Long id;
    private String name;
    private String email;

    protected UserDocument(){}

    private List<QuestionDocument> questions;

    public UserDocument(Long id,
                        String name,
                        String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
