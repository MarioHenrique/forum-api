package com.forumapi.features.votes.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class VoteUpdateTO {

    @Min(value = 1, message = "A pontuação minima é 1")
    @Max(value = 5, message = "A pontuação máxima é 5")
    @ApiModelProperty(required = true)
    private Integer score;

}
