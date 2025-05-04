package com.example.aurora.features.user.domain.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestModel {
  private String fullname;

  private String phoneNumber;

  private String email;

  private String password;

  public void fixPhoneNumberShape() {
    phoneNumber = phoneNumber.replaceAll("[()\\s\\-]", "");
  }
}
