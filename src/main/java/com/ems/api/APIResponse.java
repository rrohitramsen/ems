package com.ems.api;

/**
 * Created by rohitkumar on 28/01/18.
 */
public class APIResponse<T> {

    private final String message;
    private final T entity;

    public APIResponse(String message, T entity) {
        this.message = message;
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "message='" + message + '\'' +
                ", basicEntity=" + entity +
                '}';
    }
}
