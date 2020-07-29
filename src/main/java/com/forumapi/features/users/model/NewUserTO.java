package com.forumapi.features.users.model;

import com.forumapi.enums.RoleEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class NewUserTO {

    @NotNull(message = "O nome é obrigatório")
    @ApiModelProperty(required = true)
    private String name;

    @NotNull(message = "A data de nascimento é obrigatório")
    @ApiModelProperty(required = true, example = "1994-04-04")
    private LocalDate birthDay;

    @Email(message = "Email invalido")
    @NotNull(message = "O email é obrigatório")
    @ApiModelProperty(required = true)
    private String email;

    @NotNull(message = "A senha é obrigatório")
    @Size(min = 4, max = 10, message = "A senha precisa conter entre 4 e 10 caracteres")
    @ApiModelProperty(required = true)
    private String password;

    @NotNull(message = "O perfil do usuário é obrigatório")
    @ApiModelProperty(required = true)
    private RoleEnum role;

}
