package com.forumapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reputation extends History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Long score;

    protected Reputation(){}

    public Reputation(User user){
        this.user = user;
        this.score = 0L;
    }

}
