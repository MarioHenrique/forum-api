package com.forumapi.features.users.business;

import com.forumapi.entities.User;
import com.forumapi.features.users.exceptions.UserAlreadyRegistered;
import com.forumapi.features.users.infra.UserRepository;
import com.forumapi.features.users.model.NewUserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NewUserValidator {

    private final UserRepository userRepository;

    @Autowired
    public NewUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(NewUserTO newUserTO){
        Optional<User> user = userRepository.findByEmail(newUserTO.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyRegistered();
        }
    }

}
