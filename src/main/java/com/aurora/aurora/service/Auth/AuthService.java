package com.aurora.aurora.service.Auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aurora.aurora.config.JwtSecurity;
import com.aurora.aurora.models.auth.AuthRequest;
import com.aurora.aurora.models.auth.AuthResponse;
import com.aurora.aurora.models.user.UserModel;
import com.aurora.aurora.service.User.UserService;
import com.aurora.aurora.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNull;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserService userService;
  private final Map<String, String> refreshStorage = new HashMap<>();
  private final JwtUtil jwtUtil;

  public AuthResponse login(@NonNull AuthRequest authRequest) throws AuthException {
    final UserModel user = userService.getUserByEmail(authRequest.getEmail())
        .orElseThrow(() -> new AuthException("Пользователь не найден"));
    if (user.getPassword().equals(authRequest.getPassword())) {
      final String accessToken = jwtUtil.generateAccessToken(user);
      final String refreshToken = jwtUtil.generateRefreshToken(user);
      refreshStorage.put(user.getEmail(), refreshToken);
      return new AuthResponse(accessToken, refreshToken);
    } else {
      throw new AuthException("Неправильный пароль");
    }
  }

  public AuthResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
    if (jwtUtil.validateRefreshToken(refreshToken)) {
      final Claims claims = jwtUtil.getRefreshClaims(refreshToken);
      final String login = claims.getSubject();
      final String saveRefreshToken = refreshStorage.get(login);
      if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
        final UserModel user = userService.getUserByEmail(login)
            .orElseThrow(() -> new AuthException("Пользователь не найден"));
        final String accessToken = jwtUtil.generateAccessToken(user);
        return new AuthResponse(accessToken, refreshToken);
      }
    }
    return new AuthResponse(null, null);
  }

  public AuthResponse refresh(@NonNull String refreshToken) throws AuthException {
    if (jwtUtil.validateRefreshToken(refreshToken)) {
      final Claims claims = jwtUtil.getRefreshClaims(refreshToken);
      final String login = claims.getSubject();
      final String saveRefreshToken = refreshStorage.get(login);
      if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
        final UserModel user = userService.getUserByEmail(login)
            .orElseThrow(() -> new AuthException("Пользователь не найден"));
        final String accessToken = jwtUtil.generateAccessToken(user);
        final String newRefreshToken = jwtUtil.generateRefreshToken(user);
        refreshStorage.put(user.getEmail(), newRefreshToken);
        return new AuthResponse(accessToken, newRefreshToken);
      }
    }
    throw new AuthException("Невалидный JWT токен");
  }

  public JwtSecurity getAuthInfo() {
    return (JwtSecurity) SecurityContextHolder.getContext().getAuthentication();
  }
}
