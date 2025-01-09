package com.aurora.aurora.models.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
  private String email;
  private String password;
}
