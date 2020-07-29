package com.forumapi.features.flags.model;

import com.forumapi.entities.Flag;
import lombok.Getter;

@Getter
public class FlagVO {

    private final Long id;
    private final String description;

    public FlagVO(Flag flag){
        id = flag.getId();
        description = flag.getDescription();
    }

}
