package org.ht.model.response;

import lombok.Data;
import org.ht.model.vo.UserVo;

@Data
public class LoginResponse {
    private String token;
    private UserVo userVo;
} 