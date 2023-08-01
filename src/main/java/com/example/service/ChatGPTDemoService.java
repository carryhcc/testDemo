package com.example.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2023/3/22 09:55
 */
public interface ChatGPTDemoService {

    /**
     * 最简使用
     * @param msg
     * @return
     */
   public Object minimalUse(String msg);

    /**
     * 进阶使用
     * @param msg
     * @return
     */
    public Object advancedUse(String aiSystem ,String msg);

    /**
     * 流式使用
     * @param msg
     * @return
     */
    public Object flowUse(String msg);

    /**
     * 流式配合Spring SseEmitter使用
     * @param msg
     * @return
     */
    public Object SseEmitterUse(String msg);

}
