package com.aurora.aurora.service.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.aurora.models.register.RegisterRequest;
import com.aurora.aurora.models.user.UserModel;
import com.aurora.aurora.repositories.UserRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public Optional<UserModel> getUserByEmail(@NonNull String email) {
    return Optional.ofNullable(userRepository.findByEmail(email));
  }

  public Optional<UserModel> getUserByPhoneNumber(@NonNull String phoneNumber) {
    return Optional.ofNullable(userRepository.findByPhoneNumber(phoneNumber));
  }

  public Optional<UserModel> createUser(@NonNull RegisterRequest registerRequest) {
    UserModel newUser = userRepository.createUser(registerRequest);

    return Optional.ofNullable(newUser);
  }
}
