package com.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/2/9 11:32
 */
@Data
@TableName(value = "emoji_msg")
public class EmojiMsg {
    @TableId(value = "ID")
    private Long id;
    @TableField(value = "MSG")
    private String msg;
    @TableField(value = "IP")
    private String ip;
    @TableField(value = "CREATE_AT")
    private Date createAt;
}
