package com.example.controller;

import com.example.model.Result;
import com.example.model.annotation.MethodLog;
import com.example.model.enmu.ResultEnum;
import com.example.service.ChatMsgService;
import com.example.service.KeyManager;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.net.Proxy;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2023/3/23 14:44
 */
@RefreshScope
@Slf4j
@RestController
@RequestMapping("/chatGPT")
public class ChatGPTController {

    @Value("${server.env}")
    String env;
    @Resource
    private KeyManager keyManager;

    @Resource
    private ChatMsgService chatMsgService;

    Proxy proxy = Proxys.http("127.0.0.1", 7890);
    @MethodLog
    @PostMapping("/send")
    public Result chatGPT(@RequestParam String msg) {
        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(keyManager.getKey())
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build()
                .init();
        String answer = "";
        try {
            answer = chatGPT.chat(msg).trim();
            log.info("问：" + msg);
            log.info("答：" + answer);
            chatMsgService.saveMsg(msg, answer);
        } catch (Exception e) {
            log.warn("Please try again later, error msg ：{}",e.getMessage());
            return Result.error(e.getMessage());
        }
        return Result.build(ResultEnum.SUCCESS.getCode(),msg,answer);
    }


    @GetMapping("/chat/sse")
    @CrossOrigin
    public SseEmitter sseEmitter(String prompt) {
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .timeout(600)
                .apiKey(keyManager.getKey())
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build()
                .init();
        SseEmitter sseEmitter = new SseEmitter(-1L);
        SseStreamListener listener = new SseStreamListener(sseEmitter);
        Message message = Message.of(prompt);
        listener.setOnComplate(msg -> {
            // 回答完成，可以做一些事情
        });
        chatGPTStream.streamChatCompletion(List.of(message), listener);
        return sseEmitter;
    }

}
