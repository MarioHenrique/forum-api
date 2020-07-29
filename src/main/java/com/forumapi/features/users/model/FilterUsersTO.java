package com.forumapi.features.users.model;

import com.forumapi.persistence.Pagination;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class FilterUsersTO extends Pagination {

    private String email;
    private String name;

    @ApiModelProperty(example = "1994-04-04")
    private LocalDate birthDay;

}
