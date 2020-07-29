package com.forumapi.features.users.usecase;

import com.forumapi.entities.User;
import com.forumapi.features.users.business.UpdateUserValidator;
import com.forumapi.features.users.business.UserUpdater;
import com.forumapi.features.users.exceptions.UserNotFoundException;
import com.forumapi.features.users.infra.UserRepository;
import com.forumapi.features.users.model.UpdateUserTO;
import com.forumapi.features.users.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UpdateUserValidator updateUserValidator;
    private final UserUpdater userUpdater;

    @Autowired
    public UpdateUserUseCase(UserRepository userRepository,
                             UpdateUserValidator updateUserValidator, UserUpdater userUpdater) {
        this.userRepository = userRepository;
        this.updateUserValidator = updateUserValidator;
        this.userUpdater = userUpdater;
    }

    @Transactional
    public UserVO updateUser(Long id, UpdateUserTO updateUserTO){
        updateUserValidator.validate(id);
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userUpdater.update(user, updateUserTO);
        userRepository.save(user);
        return new UserVO(user);
    }

}
