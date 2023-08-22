package com.snsproject.sns_service.controller.response;

import com.snsproject.sns_service.model.User;
import com.snsproject.sns_service.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String userName;
    private UserRole userRole;

    public static UserResponse formUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getUserRole()
        );
    }
}
