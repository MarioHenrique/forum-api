package com.forumapi.features.questions.model;

import com.forumapi.entities.Flag;
import com.forumapi.entities.Question;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionVO {

    private final Long id;
    private final String description;
    private final Long userId;
    private final boolean resolved;
    private final List<String> flags;

    public QuestionVO(Question question) {
        this.id = question.getId();
        this.description = question.getDescription();
        this.userId = question.getUserId();
        this.resolved = question.isResolved();
        this.flags = question
                .getFlags()
                .stream()
                .map(Flag::getDescription)
                .collect(Collectors.toList());
    }

}
