package com.aurora.aurora.controller.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.aurora.models.auth.AuthRequest;
import com.aurora.aurora.models.auth.AuthResponse;
import com.aurora.aurora.models.auth.RefreshJwtRequest;
import com.aurora.aurora.service.Auth.AuthService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @PostMapping("login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
    try {
      final AuthResponse token = authService.login(authRequest);
      return ResponseEntity.ok(token);
    } catch (AuthException e) {
      logger.error("Authentication failed: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (Exception e) {
      logger.error("An error occurred during login: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }

  @PostMapping("token")
  public ResponseEntity<AuthResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
    try {
      final AuthResponse token = authService.getAccessToken(request.getRefreshToken());
      return ResponseEntity.ok(token);
    } catch (AuthException e) {
      logger.error("Authentication failed: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (Exception e) {
      logger.error("An error occurred during login: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PostMapping("refresh")
  public ResponseEntity<AuthResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
    try {
      final AuthResponse token = authService.refresh(request.getRefreshToken());
      return ResponseEntity.ok(token);
    } catch (AuthException e) {
      logger.error("Authentication failed: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (Exception e) {
      logger.error("An error occurred during login: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}
