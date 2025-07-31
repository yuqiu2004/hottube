package org.ht.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {

    private Integer uid;

    private String username;

    private String nickname;

    private Integer gender; // 0 女，1 男，2 未知

}
