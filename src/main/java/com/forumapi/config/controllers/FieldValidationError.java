package com.forumapi.config.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldValidationError {

    private String fieldName;
    private String message;

}
