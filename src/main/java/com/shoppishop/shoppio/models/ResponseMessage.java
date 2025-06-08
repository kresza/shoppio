package com.shoppishop.shoppio.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseMessage {

    private String message;
    @Builder.Default private Long code = 200L;
}
