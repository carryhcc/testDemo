package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.SyncMsgMapper;
import com.example.model.SyncMsg;
import com.example.service.SyncMsgService;
import org.springframework.stereotype.Service;

/**
 * 消息中心表 服务实现类
 * @author hcc
 * @since 2022-02-09
 */
@Service
public class SyncMsgServiceImpl extends ServiceImpl<SyncMsgMapper, SyncMsg> implements SyncMsgService {

}
