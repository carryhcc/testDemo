package com.example.controller.mq;


import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@RabbitListener(queues = "TestDirectQueue", concurrency = "1")//监听的队列名称 TestDirectQueue
public class QueueConsumerListener {


    @RabbitHandler
    public void process(Map testMessage) throws InterruptedException {
        Thread.sleep(5000);
        log.info("DirectReceiver消费者收到消息:{}",testMessage);
    }
}