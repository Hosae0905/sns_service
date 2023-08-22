package com.snsproject.sns_service.controller.response;

import com.snsproject.sns_service.model.User;
import com.snsproject.sns_service.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse {

    private String token;
}
