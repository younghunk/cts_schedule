package com.schedule.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Response {
    XML("application/xml"),
    JSON("application/json");
    private final String responseType;
}
