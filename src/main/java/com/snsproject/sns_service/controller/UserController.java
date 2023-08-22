package com.snsproject.sns_service.controller;

import com.snsproject.sns_service.controller.request.UserJoinRequest;
import com.snsproject.sns_service.controller.request.UserLoginRequest;
import com.snsproject.sns_service.controller.response.Response;
import com.snsproject.sns_service.controller.response.UserJoinResponse;
import com.snsproject.sns_service.controller.response.UserLoginResponse;
import com.snsproject.sns_service.model.User;
import com.snsproject.sns_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // TODO : implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {

        //join
        User user = userService.join(request.getUserName(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(user));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getUserName(), request.getPassword());
        return Response.success(new UserLoginResponse(token));
    }
}
