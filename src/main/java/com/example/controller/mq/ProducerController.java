package com.example.controller.mq;

import cn.hutool.core.date.DateUtil;
import com.example.config.RedisUtil;
import com.example.model.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class ProducerController {
    @Resource
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Resource
    RedisUtil redisUtil;

    @GetMapping("/sendDirectMessage")
    public Result sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = DateUtil.formatTime(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机 TestDirectExchange
        log.info("发送消息,{}", map);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        // 添加到redis
        redisUtil.set(messageId, map.toString());
        // 1分钟后过期
        redisUtil.expire(messageId, 1, TimeUnit.MINUTES);
        return Result.success();
    }
}