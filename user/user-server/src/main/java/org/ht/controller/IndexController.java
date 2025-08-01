package org.ht.controller;

import jakarta.annotation.Resource;
import org.ht.model.request.RegisterRequest;
import org.ht.model.request.LoginRequest;
import org.ht.model.request.UpdateUserRequest;
import org.ht.model.response.*;
import org.ht.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class IndexController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Resp<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse resp = userService.register(registerRequest);
        return Resp.success(resp);
    }

    @PostMapping("/login")
    public Resp<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse resp = userService.login(loginRequest);
        return Resp.success(resp);
    }

    @GetMapping("/info/{uid}")
    public Resp<UserInfoResponse> getUserInfo(@PathVariable Integer uid) {
        UserInfoResponse resp = userService.getUserInfo(uid);
        return Resp.success(resp);
    }

    @PutMapping("/info/{uid}")
    public Resp<UserInfoResponse> updateUserInfo(@PathVariable Integer uid, @RequestBody UpdateUserRequest request) {
        UserInfoResponse resp = userService.updateUserInfo(uid, request);
        return Resp.success(resp);
    }

    @GetMapping("/upload/image/{imageName}")
    public Resp<UploadImageResponse> uploadImage(@PathVariable String imageName) {
        UploadImageResponse resp = userService.uploadImage(imageName);
        return Resp.success(resp);
    }
}
