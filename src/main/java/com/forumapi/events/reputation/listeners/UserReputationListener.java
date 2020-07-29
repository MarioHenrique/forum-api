package com.forumapi.events.reputation.listeners;

import com.forumapi.events.reputation.source.UserReputationEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserReputationListener {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserReputationListener(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @TransactionalEventListener
    @Async
    public void calculateUserReputation(UserReputationEventSource event) {
        List<Long> usersId = (List<Long>)event.getSource();
        usersId.forEach(this::updateUserReputarion);
    }

    private void updateUserReputarion(Long userId){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);
        jdbcTemplate.update("update reputation set score = (select sum(score) from vote where answer_id in (select id from answer where user_id = :userId)) where user_id = :userId", params);
    }

}
