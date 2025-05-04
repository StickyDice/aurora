package com.example.aurora.features.user.domain.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.aurora.features.user.domain.requestModel.UserRegisterRequestModel;
import com.example.aurora.support.helper.Helper;
import com.example.aurora.support.validator.ValidatorResponse;

@Component
public class UserCreationParamsValidator {
  private static Pattern emailPattern = Pattern.compile("^\\S+@\\S+\\.\\S+$");

  private static Pattern phoneNumberPattern = Pattern.compile("^\\+?[1-9][0-9]{7,14}$");

  public ValidatorResponse emailValidator(String email) {
    if (email.length() == 0)
      return new ValidatorResponse("Email is required", false);

    Matcher matcher = emailPattern.matcher(email);

    if (!matcher.matches())
      return new ValidatorResponse("Incorrect email", false);
    ;

    return new ValidatorResponse(null, true);
  }

  public ValidatorResponse phoneNumberValidator(String phoneNumber) {
    String removeSeparatorsRegex = "[()\\-\s]";
    String cleanPhoneNumber = phoneNumber.replaceAll(removeSeparatorsRegex, "");

    if (cleanPhoneNumber.length() < 10)
      return new ValidatorResponse("Phone number should contain at least 10 symbols", false);

    Matcher matcher = phoneNumberPattern.matcher(cleanPhoneNumber);

    if (!matcher.matches()) {
      return new ValidatorResponse("Phone number doesn't match pattern", false);
    }

    return new ValidatorResponse(null, true);
  }

  public ValidatorResponse fullnameValidator(String fullname) {
    if (fullname.length() < 5)
      return new ValidatorResponse("Fullname should contain at least five chars", false);

    return new ValidatorResponse(null, true);
  }

  public ValidatorResponse validateRequestModel(UserRegisterRequestModel requestModel) throws Exception {
    ValidatorResponse isPhoneValid = phoneNumberValidator(requestModel.getPhoneNumber());
    ValidatorResponse isEmailValid = emailValidator(requestModel.getEmail());
    ValidatorResponse isFullnameValid = fullnameValidator(requestModel.getFullname());

    String finalResponseMessage = Helper.firstOf(isPhoneValid.getMessage(), isEmailValid.getMessage(),
        isFullnameValid.getMessage(), "Valid");

    ValidatorResponse finalResponse = new ValidatorResponse(finalResponseMessage,
        isPhoneValid.getIsValid() && isEmailValid.getIsValid() && isFullnameValid.getIsValid());

    return finalResponse;
  }
}
