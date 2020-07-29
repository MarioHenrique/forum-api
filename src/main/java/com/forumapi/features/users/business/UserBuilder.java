package com.forumapi.features.users.business;

import com.forumapi.entities.User;
import com.forumapi.features.users.model.NewUserTO;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    public User createUser(NewUserTO newUserTO, String passwordEncrypted) {
        return new User(newUserTO.getEmail(),
                newUserTO.getName(),
                passwordEncrypted,
                newUserTO.getBirthDay())
                .addRole(newUserTO.getRole());
    }

}
