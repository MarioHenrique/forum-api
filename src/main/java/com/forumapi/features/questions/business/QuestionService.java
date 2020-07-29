package com.forumapi.features.questions.business;

import com.forumapi.entities.Question;
import com.forumapi.features.questions.exceptions.QuestionNotFoundException;
import com.forumapi.features.questions.infra.QuestionRepository;
import com.forumapi.features.questions.model.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Transactional
    public QuestionVO resolve(Long questionId){
        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
        question.resolve();
        questionRepository.save(question);
        return new QuestionVO(question);
    }

    public QuestionVO searchQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
        return new QuestionVO(question);
    }

}
