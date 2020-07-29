package com.forumapi.features.info.model;

import com.forumapi.documents.UserDocument;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserInfoVO {

    private final Long userId;
    private final String name;
    private final String email;
    private List<QuestionInfoVO> questions;

    public UserInfoVO(UserDocument userDocument){
        this.userId = userDocument.getId();
        this.name = userDocument.getName();
        this.email = userDocument.getEmail();
        this.questions = userDocument
                .getQuestions()
                .stream()
                .map(QuestionInfoVO::new)
                .collect(Collectors.toList());
    }

}
