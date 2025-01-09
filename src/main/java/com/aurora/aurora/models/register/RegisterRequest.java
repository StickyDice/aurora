package com.aurora.aurora.models.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
  /* -------------------------------------------------------------------------- */
  private String fullname;

  /* -------------------------------------------------------------------------- */
  private String password;

  /* -------------------------------------------------------------------------- */
  private String phoneNumber;

  /* -------------------------------------------------------------------------- */
  private String email;
}
