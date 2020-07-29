package com.forumapi.features.users.business;

import com.forumapi.entities.User;
import com.forumapi.features.users.model.UpdateUserTO;
import org.springframework.stereotype.Component;

@Component
public class UserUpdater {

    public void update(User user, UpdateUserTO updateUserTO){
        user.setName(updateUserTO.getName());
        user.setBirthDay(updateUserTO.getBirthDay());
    }

}
