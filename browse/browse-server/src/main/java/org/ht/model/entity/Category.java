package org.ht.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value ="category")
@Data
public class Category {

    @TableId
    private Long id;

    private String name;

    private Date createTime;
}