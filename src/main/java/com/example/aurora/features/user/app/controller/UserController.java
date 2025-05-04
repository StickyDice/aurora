package com.example.aurora.features.user.app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aurora.features.user.app.dto.UserDTO;
import com.example.aurora.features.user.app.mapper.UserMapper;
import com.example.aurora.features.user.domain.entity.User;
import com.example.aurora.features.user.domain.requestModel.UserRegisterRequestModel;
import com.example.aurora.features.user.domain.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private IUserService userService;

  @PostMapping("/")
  public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestModel requestModel) {
    try {
      userService.create(requestModel);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

    return ResponseEntity.ok().body("ok");
  }

  @GetMapping("")
  public ResponseEntity<?> getUser(@RequestParam UUID id) {
    try {
      User user = userService.getUserById(id);
      UserDTO userDto = UserMapper.mapToUserDTO(user);

      return ResponseEntity.ok().body(userDto);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
