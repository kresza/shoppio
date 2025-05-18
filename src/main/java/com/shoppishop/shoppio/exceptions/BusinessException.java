package com.shoppishop.shoppio.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessException extends RuntimeException {
  private final Long code;
  private final String message;
}
