package org.ht.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoVo {
    private Long id;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 封面图片URL
     */
    private String coverUrl;

    /**
     * 视频时长（秒）
     */
    private Long durationSeconds;

    /**
     * 播放次数
     */
    private Long playCount;

    /**
     * 创建者ID
     */
    private Integer creatorId;

    /**
     * 创建者nickname
     */
    private String nickname;

}
