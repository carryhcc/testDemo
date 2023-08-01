package com.example.service.impl;

import com.example.service.ChatGPTDemoService;
import com.example.service.KeyManager;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import jakarta.annotation.Resource;

import java.net.Proxy;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2023/3/22 09:55
 */
public class ChatGPTDemoServiceImpl implements ChatGPTDemoService {
    @Resource
    private KeyManager keyManager;
    @Override
    public Object minimalUse(String msg) {
        //国内需要代理
        Proxy proxy = Proxys.http("127.0.0.1", 1080);

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(keyManager.getKey())
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build()
                .init();

        String res = chatGPT.chat(msg);
        System.out.println(res);
        return res;
    }

    @Override
    public Object advancedUse(String aiSystem,String msg) {
        //国内需要代理 国外不需要
        Proxy proxy = Proxys.http("127.0.0.1", 1080);

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(keyManager.getKey())
                .proxy(proxy)
                .timeout(900)
                .apiHost("https://api.openai.com/")
                .build()
                .init();

        // Message system = Message.ofSystem("你现在是一个诗人，专门写七言绝句");
        Message system = Message.ofSystem(aiSystem);
        Message message = Message.of(msg);
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .messages(Arrays.asList(system, message))
                .maxTokens(3000)
                .temperature(0.9)
                .build();
        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        System.out.println(res);
        return res;
    }

    @Override
    public Object flowUse(String msg) {
        //国内需要代理 国外不需要
        Proxy proxy = Proxys.http("127.0.0.1", 1080);

        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .timeout(600)
                .apiKey(keyManager.getKey())
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build()
                .init();


        ConsoleStreamListener listener = new ConsoleStreamListener();
        Message message = Message.of(msg);
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .messages(Arrays.asList(message))
                .build();
        chatGPTStream.streamChatCompletion(chatCompletion, listener);
        return chatCompletion;
    }

    @Override
    public Object SseEmitterUse(String msg) {
        return null;
    }
}
