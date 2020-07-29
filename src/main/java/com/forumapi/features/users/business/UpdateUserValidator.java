package com.forumapi.features.users.business;

import com.forumapi.entities.User;
import com.forumapi.features.users.exceptions.UserNotFoundException;
import com.forumapi.features.users.exceptions.UserUpdateUnauthorizedException;
import com.forumapi.features.users.infra.UserRepository;
import com.forumapi.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserValidator {

    private final UserRepository userRepository;
    private final PrincipalService principalService;

    @Autowired
    public UpdateUserValidator(UserRepository userRepository, PrincipalService principalService) {
        this.userRepository = userRepository;
        this.principalService = principalService;
    }

    public void validate(Long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        String email = principalService.getEmailFromAuthenticationUser();
        if (!user.getEmail().equals(email)){
            throw new UserUpdateUnauthorizedException();
        }
    }

}
