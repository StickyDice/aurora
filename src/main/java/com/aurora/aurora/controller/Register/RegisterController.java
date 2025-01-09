package com.aurora.aurora.controller.Register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.aurora.models.auth.AuthResponse;
import com.aurora.aurora.models.register.RegisterRequest;

@RestController
@RequestMapping("api/register")
public class RegisterController {
  @PostMapping
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {

  }
}
