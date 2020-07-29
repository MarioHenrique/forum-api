package com.forumapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
public class Question extends History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Flag> flags;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers;

    private boolean resolved;

    protected Question(){}

    public Question(User user,
                    String description,
                    List<Flag> flags){
        this.user = user;
        this.description = description;
        this.flags = flags;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void changeFlags(List<Flag> flags){
        this.flags.clear();
        this.flags.addAll(flags);
    }

    public void resolve(){
        this.resolved = true;
    }

    public Long getUserId(){
        return this.user.getId();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Flag> getFlags() {
        return Collections.unmodifiableList(flags);
    }

    public boolean isOwner(User user) {
        return this.user.getEmail().equals(user.getEmail());
    }

}
