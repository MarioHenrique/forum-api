package com.forumapi.features.users.web;

import com.forumapi.features.users.business.UserSevice;
import com.forumapi.features.users.model.FilterUsersTO;
import com.forumapi.features.users.model.NewUserTO;
import com.forumapi.features.users.model.UpdateUserTO;
import com.forumapi.features.users.model.UserVO;
import com.forumapi.features.users.usecase.CreateUserUseCase;
import com.forumapi.features.users.usecase.UpdateUserUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UserSevice userSevice;

    @Autowired
    public UserController(CreateUserUseCase createUserUseCase,
                          UpdateUserUseCase updateUserUseCase,
                          UserSevice userSevice) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.userSevice = userSevice;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um novo usuário")
    public UserVO newUser(@Valid @RequestBody NewUserTO newUser){
        return createUserUseCase.create(newUser);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera um usuário", authorizations = { @Authorization(value="token") })
    public UserVO updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserTO updateUserTO){
        return updateUserUseCase.updateUser(id, updateUserTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca um único usuário", authorizations = { @Authorization(value="token") })
    public UserVO findUser(@PathVariable Long id){
        return userSevice.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca usuários", authorizations = { @Authorization(value="token") })
    public Page<UserVO> findUsers(@Valid FilterUsersTO filterUsersTO){
        return userSevice.findUsers(filterUsersTO);
    }

}
