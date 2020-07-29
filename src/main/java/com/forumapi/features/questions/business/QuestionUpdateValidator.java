package com.forumapi.features.questions.business;

import com.forumapi.features.questions.model.QuestionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionUpdateValidator {

    private final QuestionOwnerValidator questionOwnerValidator;
    private final QuestionFlagValidator questionFlagValidator;

    @Autowired
    public QuestionUpdateValidator(QuestionOwnerValidator questionOwnerValidator,
                                   QuestionFlagValidator questionFlagValidator) {
        this.questionOwnerValidator = questionOwnerValidator;
        this.questionFlagValidator = questionFlagValidator;
    }

    public void validate(Long questionId, QuestionTO questionTO){
        questionFlagValidator.validate(questionTO);
        questionOwnerValidator.validate(questionId);
    }

}
