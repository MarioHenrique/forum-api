package com.forumapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Answer extends History implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String coments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "answer")
    private List<Vote> votes;

    protected Answer(){}

    public Answer(Question question,
                  User user,
                  String coments){

        this.question = question;
        this.user = user;
        this.coments = coments;
    }

    public Long getId() {
        return id;
    }

    public Long getQuestionId(){
        return question.getId();
    }

    public Answer setComents(String coments) {
        this.coments = coments;
        return this;
    }

    public String getComents() {
        return coments;
    }

    public boolean isOwner(User user) {
        return this.user.getEmail().equals(user.getEmail());
    }
}
