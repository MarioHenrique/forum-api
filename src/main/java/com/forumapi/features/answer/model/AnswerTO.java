package com.forumapi.features.answer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AnswerTO {

    @NotNull(message = "A questão é obrigatória")
    @ApiModelProperty(required = true)
    private Long questionId;

    @NotNull(message = "O comentário é obrigatório")
    @Size(min=10, max = 250, message = "O comentário precisa ter entre 10 a 250 caracteres")
    @ApiModelProperty(required = true)
    private String coments;

}
