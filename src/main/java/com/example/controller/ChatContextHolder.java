package com.example.controller;
import com.plexpt.chatgpt.entity.chat.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2023/3/22 10:02
 */
public class ChatContextHolder {

    private static final Map<String, List<Message>> context = new HashMap<>();


    /**
     * 获取对话历史
     *
     * @param id
     * @return
     */
    public static List<Message> get(String id) {
        return context.computeIfAbsent(id, k -> new ArrayList<>());
    }


    /**
     * 添加对话
     *
     * @param id
     * @return
     */
    public static void add(String id, String msg) {

        Message message = Message.builder().content(msg).build();
        add(id, message);
    }


    /**
     * 添加对话
     *
     * @param id
     * @return
     */
    public static void add(String id, Message message) {
        List<Message> messages = context.computeIfAbsent(id, k -> new ArrayList<>());
        messages.add(message);
    }
}