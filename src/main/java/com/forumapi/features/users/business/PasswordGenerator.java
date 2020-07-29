package com.forumapi.features.users.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordGenerator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String generatePassword(String userPassword){
        return new StringBuilder()
                .append("{bcrypt}")
                .append(passwordEncoder.encode(userPassword))
                .toString();
    }


}
