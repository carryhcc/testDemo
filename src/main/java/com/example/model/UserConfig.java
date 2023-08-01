package com.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author cchu
 * @TableName USER_CONFIG
 */
@TableName(value = "USER_CONFIG")
@Data
public class UserConfig implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 权限()
     */
    private String role;
    /**
     * 是否启用0:代表false、1:代表true
     */
    private Integer isEnabled;
    /**
     * 添加时间
     */
    private Date createAt;
    /**
     * 更新时间
     */
    private Date updateAt;
    /**
     * 会员等级
     */
    private String userLevel;
    /**
     * 备注
     */
    private String remarks;
    /**
     * IP地址
     */
    private String ip;
    /**
     * MAC地址
     */
    private String mac;
    /**
     * 付款方式
     */
    private String payType;
    /**
     * 信用卡类型
     */
    private String creditCardsType;
    /**
     * 信用卡卡号
     */
    private String creditCardsNumber;
    /**
     * 信用卡日期
     */
    private String creditCardsDate;
}