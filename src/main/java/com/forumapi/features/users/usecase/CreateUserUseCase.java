package com.forumapi.features.users.usecase;

import com.forumapi.entities.User;
import com.forumapi.features.users.business.NewUserValidator;
import com.forumapi.features.users.business.PasswordGenerator;
import com.forumapi.features.users.business.UserBuilder;
import com.forumapi.features.users.business.UserCacheEventPublisher;
import com.forumapi.features.users.infra.UserRepository;
import com.forumapi.features.users.model.NewUserTO;
import com.forumapi.features.users.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final NewUserValidator newUserValidator;
    private final UserBuilder userBuilder;
    private final UserCacheEventPublisher userCacheEventPublisher;

    @Autowired
    public CreateUserUseCase(UserRepository userRepository,
                             PasswordGenerator passwordGenerator,
                             NewUserValidator newUserValidator,
                             UserBuilder userBuilder,
                             UserCacheEventPublisher userCacheEventPublisher) {
        this.userRepository = userRepository;
        this.passwordGenerator = passwordGenerator;
        this.newUserValidator = newUserValidator;
        this.userBuilder = userBuilder;
        this.userCacheEventPublisher = userCacheEventPublisher;
    }

    @Transactional
    public UserVO create(NewUserTO newUserTO){
        newUserValidator.validate(newUserTO);
        String passwordEncrypted = passwordGenerator.generatePassword(newUserTO.getPassword());
        User user = userBuilder.createUser(newUserTO, passwordEncrypted);
        userRepository.save(user);
        userCacheEventPublisher.onCreateUser(user);
        return new UserVO(user);
    }

}
