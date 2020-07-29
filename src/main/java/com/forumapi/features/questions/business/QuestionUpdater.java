package com.forumapi.features.questions.business;

import com.forumapi.entities.Flag;
import com.forumapi.entities.Question;
import com.forumapi.features.flags.infra.FlagRepository;
import com.forumapi.features.questions.model.QuestionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionUpdater {

    private final FlagRepository flagRepository;

    @Autowired
    public QuestionUpdater(FlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    public void update(Question question, QuestionTO questionTO){
        List<Flag> flagsToUpdate = new ArrayList<>();
        Iterable<Flag> flags = flagRepository.findAllById(questionTO.getFlagsIds());
        flags.forEach(flagsToUpdate::add);

        question.setDescription(questionTO.getDescription());
        question.changeFlags(flagsToUpdate);
    }

}
