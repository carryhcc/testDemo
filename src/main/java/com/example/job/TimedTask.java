package com.example.job;

import com.example.service.WxRebootService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



/**
 * 定时任务
 *
 * @author : cchu
 * Date: 2022/1/17 13:47
 */
@Component
@Log4j2
public class TimedTask {

    @Resource
    public WxRebootService wxRebootService;

    /**
     * 每天执行一次 摸🐟
     */
    @Scheduled(cron = "0 0 10 * * ?")
    public void moFish() {
        log.info("摸鱼计时器任务开始执行");
        wxRebootService.moFish();
        log.info("摸鱼计时器任务执行结束");
    }

    /**
     * 每天18:00提醒写日报
     */
    @Scheduled(cron = "0 0 18 * * ?")
    public void logTime() {
        log.info("提醒写logTime开始执行");
        wxRebootService.runDailyMsg();
        log.info("提醒写logTime执行完毕");
    }
}
