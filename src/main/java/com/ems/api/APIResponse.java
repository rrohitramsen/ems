package com.ems.api;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by rohitkumar on 28/01/18.
 */
public class APIResponse<T> {

    @ApiModelProperty(notes = "API Response message.")
    private final String message;

    public APIResponse(String message, int returnCode, T entity) {
        this.message = message;
        this.returnCode = returnCode;
        this.entity = entity;
    }

    @ApiModelProperty(notes = "API Response return code.")
    private final int returnCode;

    @ApiModelProperty(notes = "Entity")
    private final T entity;


    public String getMessage() {
        return message;
    }

    public int getReturnCode() {
        return returnCode;
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
