package com.example.service;

import cn.hutool.core.util.DesensitizedUtil;
import com.example.config.KeyConfig;
import com.example.util.CircularBlockingQueue;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * openGPT key列表配置
 * @author cchu
 */
@Slf4j
@Service
public class KeyManager implements ApplicationRunner {

    private static final CircularBlockingQueue<String> keyQueue = new CircularBlockingQueue<>();

    @Resource
    KeyConfig config;

    public synchronized String getKey() {
        return keyQueue.next();
    }
    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("开始配置KEY队列");
            List<String> list = config.getList();
            List<String> listDesensitized = new ArrayList<>();
            for (String key : list) {
                // 脱敏处理
                listDesensitized.add(DesensitizedUtil.idCardNum(key, 3, 8));
            }
            log.info("找到" + list.size() + "个配置的KEY"+"配置为"+ Arrays.toString(listDesensitized.toArray()));
            for (String key : list) {
                keyQueue.add(key);
            }
        } catch (Exception e) {
            log.error("获取key异常: ", e);
        }
    }
}
