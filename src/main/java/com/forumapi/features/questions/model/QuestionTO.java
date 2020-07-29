package com.forumapi.features.questions.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class QuestionTO {

    @NotNull(message = "A descrição é obrigatória")
    @Size(min = 10, max = 250, message = "A descrição deve ter no minimo 10 e no máximo 250 caracteres")
    @ApiModelProperty(required = true)
    private String description;

    @NotNull(message = "As flags são obrigatórias")
    @Size(min = 1, message = "Pelo menos uma flag precisa ser passada")
    @ApiModelProperty(required = true)
    private List<Long> flagsIds;

}
