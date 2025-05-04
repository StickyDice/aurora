package com.example.aurora.support.validator;

import io.micrometer.common.lang.Nullable;
import lombok.Data;

@Data
public class ValidatorResponse {
  private String message;

  private Boolean isValid;

  public ValidatorResponse(@Nullable String message, Boolean isValid) {
    this.message = message;
    this.isValid = isValid;
  }
}
