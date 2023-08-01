package com.example.config;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2023/3/22 10:52
 */

import com.plexpt.chatgpt.listener.AbstractStreamListener;
import com.plexpt.chatgpt.util.SseHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * sse
 *
 * @author plexpt
 */
@Slf4j
@RequiredArgsConstructor
public class SseStreamListener extends AbstractStreamListener {

    final SseEmitter sseEmitter;


    @Override
    public void onMsg(String message) {
        SseHelper.send(sseEmitter, message);
    }

    @Override
    public void onError(Throwable throwable, String response) {
        SseHelper.complete(sseEmitter);
    }

}