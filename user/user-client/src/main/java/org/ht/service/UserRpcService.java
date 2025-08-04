package org.ht.service;

import org.ht.model.response.AuthResponse;
import org.ht.model.response.UserInfoResponse;

public interface UserRpcService {

    AuthResponse auth(String token);

    UserInfoResponse getUserInfo(Integer uid);

}
