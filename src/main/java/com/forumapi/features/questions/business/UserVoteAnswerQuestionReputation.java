package com.forumapi.features.questions.business;

import com.forumapi.events.reputation.source.UserReputationEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserVoteAnswerQuestionReputation {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserVoteAnswerQuestionReputation(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ApplicationEventPublisher applicationEventPublisher) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDeleteQuestion(Long questionId){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("questionId", questionId);
        List<Long> usersId = namedParameterJdbcTemplate.queryForList("select distinct user_id from vote where answer_id in (select answer_id from answer where question_id = :questionId)", params, Long.class);
        if(!usersId.isEmpty()){
            applicationEventPublisher.publishEvent(new UserReputationEventSource(usersId));
        }
    }

}
