package com.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/4/22 23:27
 */
@Data
@TableName(value = "weibo_info")
public class WeiboInfo {

    @TableId(value = "ID")
    private Long id;

    @TableField(value = "UID")
    private String uid;

    @TableField(value = "PHONE")
    private String phone;

    @TableField(value = "PHONE_CITY")
    private String phoneCity;

    @TableField(value = "WEIBO_HOME")
    private String weiboHome;

    @TableField(exist = false)
    private Integer status;

    @TableField(exist = false)
    private String message;
}
