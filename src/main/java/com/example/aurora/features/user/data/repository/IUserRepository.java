package com.example.aurora.features.user.data.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.aurora.features.user.domain.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, UUID> {
  public Boolean existsByPhoneNumber(String phoneNumber);

  public Boolean existsByEmail(String email);
}
