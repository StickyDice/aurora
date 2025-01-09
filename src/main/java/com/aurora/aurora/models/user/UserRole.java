package com.aurora.aurora.models.user;

import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole implements GrantedAuthority {
  LANDLORD("LANDLORD"),
  RESIDENT("RESIDENT");

  private final String value;

  @Override
  public String getAuthority() {
    return value;
  }
}
