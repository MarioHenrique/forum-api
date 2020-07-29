package com.forumapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Vote extends History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Integer score;

    protected Vote(){}

    public Vote(Answer answer,
                Integer score,
                User user){
        this.answer = answer;
        this.score = score;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public boolean isOwner(User user) {
        return this.user.getEmail().equals(user.getEmail());
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUserId() {
        return this.user.getId();
    }
}
