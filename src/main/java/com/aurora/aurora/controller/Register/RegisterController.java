package com.aurora.aurora.controller.Register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.aurora.controller.Auth.AuthController;
import com.aurora.aurora.models.auth.AuthResponse;
import com.aurora.aurora.models.register.RegisterRequest;
import com.aurora.aurora.service.Register.RegisterService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/register")
@RequiredArgsConstructor
public class RegisterController {
  private final RegisterService registerService;

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @PostMapping
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
    try {
      final AuthResponse token = registerService.register(registerRequest);
      return ResponseEntity.ok(token);
    } catch (AuthException e) {
      logger.error("Failed to create user: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (Exception e) {
      logger.error("Failed to create user: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }
}
