package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.EmojiMsgMapper;
import com.example.model.EmojiMsg;
import com.example.service.EmojiMsgService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/2/9 11:34
 */
@Service
public class EmojiMsgServiceImpl extends ServiceImpl<EmojiMsgMapper, EmojiMsg> implements EmojiMsgService {
}
