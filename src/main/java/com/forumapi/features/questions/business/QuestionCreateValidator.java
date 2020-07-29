package com.forumapi.features.questions.business;

import com.forumapi.features.questions.model.QuestionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionCreateValidator {

    private final QuestionFlagValidator questionFlagValidator;

    @Autowired
    public QuestionCreateValidator(QuestionFlagValidator questionFlagValidator) {
        this.questionFlagValidator = questionFlagValidator;
    }

    public void validate(QuestionTO questionTO){
        questionFlagValidator.validate(questionTO);
    }

}
