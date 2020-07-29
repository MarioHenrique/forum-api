package com.forumapi.features.users.model;

import com.forumapi.entities.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserVO {

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDate birthDay;

    public UserVO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.birthDay = user.getBirthDay();
    }

}
