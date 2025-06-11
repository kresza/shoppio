package com.shoppishop.shoppio.models;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BaseResponse {

  @Builder.Default private List<ResponseMessage> warnings = new ArrayList<>();
  @Builder.Default private List<?> data = new ArrayList<>();
  @Builder.Default private List<ResponseMessage> errors = new ArrayList<>();

  public void addError(ResponseMessage message) {
    this.errors.add(message);
  }

  public void addWarning(ResponseMessage message) {
    this.warnings.add(message);
  }
}
