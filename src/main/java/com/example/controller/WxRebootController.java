package com.example.controller;

import com.example.service.WxRebootService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 微信机器人相关配置
 *
 * @author : cchu
 * Date: 2022/1/17 13:50
 */
@Slf4j
@RestController
public class WxRebootController {

    @Resource
    private WxRebootService wxRebootService;

    @GetMapping("/moyu")
    public void moFish() {
        wxRebootService.moFish();
        log.info("摸🐟提醒执行完毕");
    }

}
