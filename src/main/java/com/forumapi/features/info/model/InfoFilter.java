package com.forumapi.features.info.model;

import com.forumapi.persistence.Pagination;
import lombok.Data;

@Data
public class InfoFilter extends Pagination {

    private String userName;
    private String email;
    private String questionDescription;

}
