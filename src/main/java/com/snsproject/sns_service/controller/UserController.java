package com.snsproject.sns_service.controller;

import com.snsproject.sns_service.controller.request.UserJoinRequest;
import com.snsproject.sns_service.controller.request.UserLoginRequest;
import com.snsproject.sns_service.controller.response.AlarmResponse;
import com.snsproject.sns_service.controller.response.Response;
import com.snsproject.sns_service.controller.response.UserJoinResponse;
import com.snsproject.sns_service.controller.response.UserLoginResponse;
import com.snsproject.sns_service.exception.ErrorCode;
import com.snsproject.sns_service.exception.SnsApplicationException;
import com.snsproject.sns_service.model.User;
import com.snsproject.sns_service.service.AlarmService;
import com.snsproject.sns_service.service.UserService;
import com.snsproject.sns_service.util.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AlarmService alarmService;


    // TODO : implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {

        //join
        User user = userService.join(request.getName(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(user));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getName(), request.getPassword());
        return Response.success(new UserLoginResponse(token));
    }

    @GetMapping("/alarm")
    public Response<Page<AlarmResponse>> alarm(Pageable pageable, Authentication authentication) {
        User user = ClassUtils.getSafeCastInstance(authentication.getName(), User.class);
        return Response.success(userService.alarmList(user.getId(), pageable).map(AlarmResponse::fromAlarm));
    }

    @GetMapping("/alarm/subscribe")
    public SseEmitter subscribe(Authentication authentication) {
        User user = ClassUtils.getSafeCastInstance(authentication.getPrincipal(), User.class);
        return alarmService.connectAlarm(user.getId());
    }
}
