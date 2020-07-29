package com.forumapi.persistence;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public abstract class Pagination {

    @NotNull(message = "É obrigatório informar a pagina na pesquisa")
    @Min(value = 1)
    @ApiModelProperty(required = true)
    private Integer page;

    @NotNull(message = "É obrigatório informar o tamanho da pagina na pesquisa")
    @Min(value = 10)
    @Max(value = 25)
    @ApiModelProperty(required = true)
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }
}
