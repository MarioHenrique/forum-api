package com.forumapi.features.questions.business;

import com.forumapi.entities.Flag;
import com.forumapi.features.flags.infra.FlagRepository;
import com.forumapi.features.questions.exceptions.InvalidFlagException;
import com.forumapi.features.questions.model.QuestionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionFlagValidator {

    private final FlagRepository flagRepository;

    @Autowired
    public QuestionFlagValidator(FlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    public void validate(QuestionTO questionTO){
        List<Flag> questionFlags = new ArrayList<>();
        Iterable<Flag> flags = flagRepository.findAllById(questionTO.getFlagsIds());
        flags.forEach(questionFlags::add);

        if(questionTO.getFlagsIds().size() > questionFlags.size()){
            List<Long> flagsRequested = questionTO.getFlagsIds();
            flagsRequested.removeIf(flagId -> questionFlags.stream().anyMatch(questionFlag -> questionFlag.getId().equals(flagId)));
            throw new InvalidFlagException(flagsRequested);
        }
    }

}
