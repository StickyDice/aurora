package com.aurora.aurora.service.Register;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aurora.aurora.models.auth.AuthResponse;
import com.aurora.aurora.models.register.RegisterRequest;
import com.aurora.aurora.models.user.UserModel;
import com.aurora.aurora.service.User.UserService;

import io.micrometer.common.lang.NonNull;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {
  private final UserService userService;

  public AuthResponse register(@NonNull RegisterRequest registerRequest) throws AuthException {
    final Optional<UserModel> user = userService.getUserByEmail(registerRequest.getEmail());
    if (user.isPresent())
      throw new AuthException("Таой пользователь уже существует");

    userService.
  }
}
