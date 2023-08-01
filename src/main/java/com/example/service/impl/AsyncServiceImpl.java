package com.example.service.impl;

import com.example.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/2/24 14:48
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync()  {
        logger.info("start executeAsync");

        System.out.println("异步线程要做的事情");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Spring自带的线程池" + Thread.currentThread().getName() + "-" +new Date());
        System.out.println("可以在这里执行批量插入等耗时的事情");

        logger.info("end executeAsync");
    }
}
