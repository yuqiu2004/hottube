package org.ht.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value ="video_category_relation")
@Data
public class VideoCategoryRelation {

    @TableId
    private Long id;

    private Long videoId;

    private Long categoryId;

    private Date createTime;
}