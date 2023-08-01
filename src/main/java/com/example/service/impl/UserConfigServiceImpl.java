package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserConfigMapper;
import com.example.model.UserConfig;
import com.example.service.UserConfigService;
import org.springframework.stereotype.Service;

/**
 * @author cchu
 * @description 针对表【USER_CONFIG】的数据库操作Service实现
 * @createDate 2022-01-11 11:35:13
 */
@Service
public class UserConfigServiceImpl extends ServiceImpl<UserConfigMapper, UserConfig> implements UserConfigService {

}




