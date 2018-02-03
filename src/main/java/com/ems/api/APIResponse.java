package com.ems.api;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by rohitkumar on 28/01/18.
 */
public class APIResponse<T> {

    @ApiModelProperty(notes = "API Response message.")
    private final String message;

    @ApiModelProperty(notes = "Entity")
    private final T entity;

    public APIResponse(String message, T entity) {
        this.message = message;
        this.entity = entity;
    }

    public String getMessage() {
        return message;
    }

    public T getEntity() {
        return entity;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "message='" + message + '\'' +
                ", basicEntity=" + entity +
                '}';
    }
}
