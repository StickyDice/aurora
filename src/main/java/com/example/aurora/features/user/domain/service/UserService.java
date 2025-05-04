package com.example.aurora.features.user.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aurora.features.user.data.repository.IUserRepository;
import com.example.aurora.features.user.domain.entity.User;
import com.example.aurora.features.user.domain.requestModel.UserRegisterRequestModel;
import com.example.aurora.features.user.domain.validator.UserCreationParamsValidator;
import com.example.aurora.support.validator.ValidatorResponse;

@Service
public class UserService implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private UserCreationParamsValidator userCreationParamsValidator;

  public User create(UserRegisterRequestModel requestModel) throws Exception {
    Boolean userAlreadyExistByPhoneNumeber = userRepository.existsByPhoneNumber(requestModel.getPhoneNumber());

    if (userAlreadyExistByPhoneNumeber) {
      throw new Exception("Phone number already taken");
    }

    Boolean userAlreadyExistByEmail = userRepository.existsByEmail(requestModel.getEmail());

    if (userAlreadyExistByEmail) {
      throw new Exception("Email already taken");
    }

    requestModel.fixPhoneNumberShape();
    ValidatorResponse isRequestModelCorrect = userCreationParamsValidator.validateRequestModel(requestModel);

    if (!isRequestModelCorrect.getIsValid()) {
      throw new Exception(isRequestModelCorrect.getMessage());
    }

    User newUser = new User();
    newUser.setFullname(requestModel.getFullname());
    newUser.setEmail(requestModel.getEmail());
    newUser.setPhoneNumber(requestModel.getPhoneNumber());
    newUser.setPassword(requestModel.getPassword());

    userRepository.save(newUser);
    return newUser;
  }

  public User getUserById(UUID id) throws Exception {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty())
      throw new Exception("User doesn't exist");

    return user.get();
  }
}
