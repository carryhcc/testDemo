package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ChatMsgMapper;
import com.example.model.ChatMsg;
import com.example.service.ChatMsgService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.Date;

/**
 * ChatMsgServiceImpl
 *
 * @author : cchu
 * Date: 2023/3/16 14:55
 */
@Service
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsg> implements ChatMsgService {

    @SneakyThrows
    @Override
    public Boolean saveMsg(String quest, String answer) {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setIp(InetAddress.getLocalHost().getHostAddress());
        chatMsg.setQuestion(quest);
        chatMsg.setAnswer(answer);
        chatMsg.setAddTime(new Date());
        return save(chatMsg);
    }
}
