package com.example.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/17 14:12
 */
public interface WxRebootService {
    /**
     * 摸鱼⏰
     */
    void moFish();

    /**
     * 自定义执行
     * @param msg
     */
    void runText(String msg);

    void runLogTimeMsg();
}
