package com.forumapi.entities;

import com.forumapi.enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends History implements Serializable {

    protected User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String email;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Reputation reputation;

    private String password;

    private LocalDate birthDay;

    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Role> roles = new HashSet<>();

    public User(String email,
                String name,
                String password,
                LocalDate birthDay) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthDay = birthDay;
        this.enabled = true;
        this.reputation = new Reputation(this);
    }

    public User addRole(RoleEnum role){
        roles.add(new Role(role, this));
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

}
