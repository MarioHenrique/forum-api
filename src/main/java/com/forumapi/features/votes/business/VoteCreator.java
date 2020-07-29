package com.forumapi.features.votes.business;

import com.forumapi.entities.Answer;
import com.forumapi.entities.Vote;
import com.forumapi.features.answer.exceptions.AnswerNotFoundException;
import com.forumapi.features.answer.infra.AnswerRepository;
import com.forumapi.features.votes.model.VoteTO;
import com.forumapi.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteCreator {

    private final AnswerRepository answerRepository;
    private final PrincipalService principalService;

    @Autowired
    public VoteCreator(AnswerRepository answerRepository,
                       PrincipalService principalService) {
        this.answerRepository = answerRepository;
        this.principalService = principalService;
    }

    public Vote create(VoteTO voteTO){
        Answer answer = answerRepository.findById(voteTO.getAnswerId()).orElseThrow(AnswerNotFoundException::new);
        return new Vote(answer,
                voteTO.getScore(),
                principalService.getUserAuthenticated());
    }

}
