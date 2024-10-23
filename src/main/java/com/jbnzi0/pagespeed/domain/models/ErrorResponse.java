package com.jbnzi0.pagespeed.domain.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
    private long timestamp;
}
