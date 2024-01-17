package com.example.model.DTO;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a chat message data transfer object.
 * This class extends the Page class and implements the Serializable interface.
 * It provides data fields to store the ID, IP, start time, and end time of a chat message.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatMsgDTO extends Page implements Serializable {

    private  Integer id;

    private  String ip;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
}
