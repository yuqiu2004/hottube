package org.ht.model.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String nickname;
    private String avatar;
    private String background;
    private Integer gender;
    private String description;
} 