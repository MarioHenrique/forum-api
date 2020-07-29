package com.forumapi.features.flags.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FlagTO {

    @NotNull(message = "A descrição é obrigatória")
    @Size(min = 5, max = 25, message = "A descrição deve ter no minomo 5 e no máximo 25 caracteres")
    @ApiModelProperty(required = true)
    private String description;

}
