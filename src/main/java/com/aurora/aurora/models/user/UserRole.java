package com.aurora.aurora.models.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
  LANDLORD("LANDLORD"),
  RESIDENT("RESIDENT");

  private final String value;

  UserRole(String value) {
    this.value = value;
  }

  @Override
  public String getAuthority() {
    return value;
  }
}
