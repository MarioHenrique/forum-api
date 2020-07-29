package com.forumapi.entities;

import com.forumapi.enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Role extends History implements Serializable {

    protected Role(){}

    @Column(name = "authority")
    @Enumerated(value = EnumType.STRING)
    private RoleEnum name;

    @EmbeddedId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public Role(RoleEnum name, User user) {
        this.name = name;
        this.user = user;
    }

}
