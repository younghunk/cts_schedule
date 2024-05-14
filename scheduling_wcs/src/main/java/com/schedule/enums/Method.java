package com.schedule.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Method {
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE");
    private final String method;
}
