package com.example.aurora.features.user.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
  private Integer id;

  private String fullname;

  private String phoneNumber;

  private String email;
}
