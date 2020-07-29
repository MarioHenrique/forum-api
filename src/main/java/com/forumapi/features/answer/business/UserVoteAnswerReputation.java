package com.forumapi.features.answer.business;

import com.forumapi.events.reputation.source.UserReputationEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserVoteAnswerReputation {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserVoteAnswerReputation(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                    ApplicationEventPublisher applicationEventPublisher) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDeleteAnswer(Long answerId){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("answerId", answerId);
        List<Long> usersId = namedParameterJdbcTemplate.queryForList("select distinct user_id from vote where answer_id = :answerId", params, Long.class);
        if(!usersId.isEmpty()){
            applicationEventPublisher.publishEvent(new UserReputationEventSource(usersId));
        }
    }

}
