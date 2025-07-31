package org.ht.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@TableName("user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Integer uid;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String background;

    private Integer gender; // 0 女，1 男，2 未知

    private String description;

    private Integer state; // 0 正常，1 封禁，2 注销

    private LocalDateTime createDatetime;

    private LocalDateTime updateDatetime;

}
