package org.ht.service;

import org.ht.model.request.RegisterRequest;
import org.ht.model.request.LoginRequest;
import org.ht.model.request.UpdateUserRequest;
import org.ht.model.response.RegisterResponse;
import org.ht.model.response.LoginResponse;
import org.ht.model.response.UploadImageResponse;
import org.ht.model.response.UserInfoResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserInfoResponse getUserInfo(Integer uid);

    UserInfoResponse updateUserInfo(Integer uid, UpdateUserRequest request);

    UploadImageResponse uploadImage(String imageName);
}
