package com.shoppishop.shoppio.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseMessage {

    private String message;
    private Long code;
}
