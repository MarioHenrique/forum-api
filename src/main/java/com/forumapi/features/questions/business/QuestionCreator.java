package com.forumapi.features.questions.business;

import com.forumapi.entities.Flag;
import com.forumapi.entities.Question;
import com.forumapi.entities.User;
import com.forumapi.features.flags.infra.FlagRepository;
import com.forumapi.features.questions.model.QuestionTO;
import com.forumapi.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionCreator {

    private final FlagRepository flagRepository;
    private final PrincipalService principalService;

    @Autowired
    public QuestionCreator(FlagRepository flagRepository,
                           PrincipalService principalService) {
        this.flagRepository = flagRepository;
        this.principalService = principalService;
    }

    public Question create(QuestionTO questionTO){
        List<Flag> questionFlags = new ArrayList<>();

        Iterable<Flag> flags = flagRepository.findAllById(questionTO.getFlagsIds());
        flags.forEach(questionFlags::add);

        User user = principalService.getUserAuthenticated();

        return new Question(user,
                questionTO.getDescription(),
                questionFlags);
    }

}
