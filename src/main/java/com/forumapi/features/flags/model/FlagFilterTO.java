package com.forumapi.features.flags.model;

import com.forumapi.persistence.Pagination;
import lombok.Data;

@Data
public class FlagFilterTO extends Pagination {

    private String description;

}
