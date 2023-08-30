package com.example.controller.mq;


import org.apache.poi.ss.formula.functions.T;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RabbitListener(queues = "TestDirectQueue", concurrency = "1")//监听的队列名称 TestDirectQueue
public class QueueConsumerListener {


    @RabbitHandler
    public void process(Map testMessage) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }
}