package com.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cchu
 * @TableName USER_INFO
 */
@TableName(value = "USER_INFO")
@Data
public class UserInfo implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    private String name;
    private Long age;
    private String email;
}