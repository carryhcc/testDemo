package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息中心表
 * @author hcc
 * @since 2022-02-09
 */
@Getter
@Setter
@TableName("sync_msg")
public class SyncMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应user_info的user_id对应
     */
    @TableField("user_agent_id")
    private String userAgentId;

    /**
     * 在线状态,0在线,1离线
     */
    @TableField("msg_type")
    private Integer msgType;

    /**
     * 药店id
     */
    @TableField("msg_pharmacy_id")
    private String msgPharmacyId;

    /**
     * 消息内容
     */
    @TableField("msg_details")
    private String msgDetails;

    /**
     * 是否已读(0:未读,1:已读)
     */
    @TableField("is_read")
    private Integer isRead;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField("update_at")
    private LocalDateTime updateAt;

    /**
     * 客户端版本
     */
    @TableField("app_version")
    private String appVersion;

    /**
     * 心跳时间(最后在线时间)
     */
    @TableField("heart")
    private LocalDateTime heart;


}
