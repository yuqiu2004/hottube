package org.ht.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 分类实体类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@TableName(value ="category")
@Data
public class Category {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}