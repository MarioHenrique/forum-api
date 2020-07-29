package com.forumapi.config.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RequestValidationError {

    private List<FieldValidationError> fields;

}
