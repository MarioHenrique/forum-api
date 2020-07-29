package com.forumapi.features.users.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString
public class UpdateUserTO {

    @NotNull(message = "O nome é obrigatório")
    @ApiModelProperty(required = true)
    private String name;

    @NotNull(message = "A data de nascimento é obrigatório")
    @ApiModelProperty(required = true, example = "1994-04-04")
    private LocalDate birthDay;

}
