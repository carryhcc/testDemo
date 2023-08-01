package com.example.model;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2023/3/16 14:53
 */
@Data
@Log4j2
@TableName("chat_msg")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMsg {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("ip")
    private String ip;
    @TableField("question")
    private String question;
    @TableField("answer")
    private String answer;
    @TableField("add_time")
    private Date addTime;

    public ChatMsg() {
    }

    public ChatMsg(String question, String answer) {
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            this.ip = "127.0.0.1";
            log.error("IP acquisition failed");
        }
        this.question = question;
        this.answer = answer;
        this.addTime = new Date();
    }
}
