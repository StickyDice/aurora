package com.aurora.aurora.config;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.aurora.aurora.models.user.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtSecurity implements Authentication {
  private boolean authenticated;
  private String email;
  private String fullname;
  private Set<UserRole> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return email;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    return fullname;
  }
}
