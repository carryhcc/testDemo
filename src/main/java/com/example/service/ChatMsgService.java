package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.ChatMsg;
import org.springframework.transaction.annotation.Transactional;

/**
 * msg保存类
 *
 * @author : cchu
 * Date: 2023/3/16 14:54
 */
@Transactional
public interface ChatMsgService extends IService<ChatMsg> {
    /**
     * 保存问答详情
     * @param quest 问题
     * @param answer 答案
     * @return
     */
    Boolean saveMsg(String quest,String answer);
}
