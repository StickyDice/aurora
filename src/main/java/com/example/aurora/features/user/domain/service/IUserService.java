package com.example.aurora.features.user.domain.service;

import java.util.UUID;

import com.example.aurora.features.user.domain.entity.User;
import com.example.aurora.features.user.domain.requestModel.UserRegisterRequestModel;

public interface IUserService {
  public User create(UserRegisterRequestModel requestModel) throws Exception;

  public User getUserById(UUID id) throws Exception;
}
