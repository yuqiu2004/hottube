package org.ht.model.service;

import org.ht.model.response.AuthResponse;

public interface UserAuthService {

    AuthResponse auth(String token);

}
