package com.example.aurora.features.user.app.mapper;

import com.example.aurora.features.user.app.dto.UserDTO;
import com.example.aurora.features.user.domain.entity.User;

public class UserMapper {
  public static UserDTO mapToUserDTO(User user) {
    UserDTO userDto = new UserDTO();

    userDto.setId(user.getId());
    userDto.setFullname(user.getFullname());
    userDto.setEmail(user.getEmail());
    userDto.setPhoneNumber(user.getPhoneNumber());

    return userDto;
  }
}
