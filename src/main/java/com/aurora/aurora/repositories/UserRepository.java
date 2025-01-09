package com.aurora.aurora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurora.aurora.models.register.RegisterRequest;
import com.aurora.aurora.models.user.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
  public UserModel findByPhoneNumber(String phoneNumber);

  public UserModel findByEmail(String email);

  public UserModel createUser(RegisterRequest registerRequest);
}
