package com.forumapi.features.answer.business;

import com.forumapi.entities.Answer;
import com.forumapi.entities.Question;
import com.forumapi.entities.User;
import com.forumapi.features.answer.model.AnswerTO;
import com.forumapi.features.questions.exceptions.QuestionNotFoundException;
import com.forumapi.features.questions.infra.QuestionRepository;
import com.forumapi.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerCreator {

    private final PrincipalService principalService;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerCreator(PrincipalService principalService,
                         QuestionRepository questionRepository) {
        this.principalService = principalService;
        this.questionRepository = questionRepository;
    }


    public Answer create(AnswerTO answerTO){
        Question question = questionRepository.findById(answerTO.getQuestionId()).orElseThrow(QuestionNotFoundException::new);
        User user = principalService.getUserAuthenticated();
        return new Answer(question,
                user,
                answerTO.getComents());
    }

}
