package com.forumapi.features.users.business;

import com.forumapi.entities.User;
import com.forumapi.features.users.exceptions.UserNotFoundException;
import com.forumapi.features.users.infra.UserRepository;
import com.forumapi.features.users.model.FilterUsersTO;
import com.forumapi.features.users.model.UserVO;
import com.forumapi.persistence.SpecificationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class UserSevice {

    private final UserRepository userRepository;

    @Autowired
    public UserSevice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserVO findById(Long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return new UserVO(user);
    }

    public Page<UserVO> findUsers(FilterUsersTO filterUsersTO){

        SpecificationQuery<User> userSpec = new SpecificationQuery<>(filterUsersTO);

        Page<User> users = userRepository.findAll(
                where(userSpec.equal("email", filterUsersTO.getEmail()))
                        .and(userSpec.like("name", filterUsersTO.getName()))
                        .and(userSpec.equal("birthDay", filterUsersTO.getBirthDay())),
                userSpec.getPagination());

        return users.map(UserVO::new);
    }

}
