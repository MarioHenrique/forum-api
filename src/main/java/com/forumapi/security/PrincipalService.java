package com.forumapi.security;

import com.forumapi.entities.User;
import com.forumapi.exceptions.AuthenticatedUserNotFound;
import com.forumapi.features.users.infra.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {

    private final UserRepository userRepository;

    @Autowired
    public PrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserAuthenticated(){
        return userRepository.findByEmail(getEmailFromAuthenticationUser()).orElseThrow(AuthenticatedUserNotFound::new);
    }

    public String getEmailFromAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
