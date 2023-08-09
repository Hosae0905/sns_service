package com.snsproject.sns_service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginJoinRequest {

    private String userName;
    private String password;
}
