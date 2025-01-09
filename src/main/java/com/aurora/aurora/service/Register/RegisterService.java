package com.aurora.aurora.service.Register;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.aurora.aurora.models.auth.AuthRequest;
import com.aurora.aurora.models.auth.AuthResponse;
import com.aurora.aurora.models.register.RegisterRequest;
import com.aurora.aurora.models.user.UserModel;
import com.aurora.aurora.service.Auth.AuthService;
import com.aurora.aurora.service.User.UserService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {
  private final UserService userService;

  private final AuthService authService;

  public AuthResponse register(@RequestBody RegisterRequest registerRequest) throws AuthException {
    final Optional<UserModel> user = userService.getUserByEmail(registerRequest.getEmail());
    if (user.isPresent())
      throw new AuthException("Пользователь с таким email уже существует");

    userService.createUser(registerRequest);

    AuthRequest newUserCredentials = new AuthRequest();
    newUserCredentials.setEmail(registerRequest.getEmail());
    newUserCredentials.setPassword(registerRequest.getPassword());
    return authService.login(newUserCredentials);
  }
}
