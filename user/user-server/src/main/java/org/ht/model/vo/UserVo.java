package org.ht.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private Integer uid;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String background;

    private Integer gender; // 0 女，1 男，2 未知

    private String description;

    private Integer state; // 0 正常，1 封禁，2 注销

}
