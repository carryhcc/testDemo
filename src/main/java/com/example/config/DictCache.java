package com.example.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.HashBiMap;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 读取字典配置并缓存
 *
 * @author : cchu
 * Date: 2021/12/16 16:51
 */
@Slf4j
@Component
public class DictCache implements ApplicationRunner {
    public static HashBiMap<String, String> biMap = HashBiMap.create();

    /**
     * 程序启动前 优先执行
     */
    @Value("${others.dict.localUrl}")
    private String localUrl;

    @Value("${others.dict.onlineUrl}")
    private String onlineUrl;

    @Value("${server.env}")
    private String env;

    @Resource
    private Environment environment;

    public static String getValue(String code) {
        if (code.length() == 1) {
            return biMap.get(code);
        } else {
            //通过value值得到key值
            return biMap.inverse().get(code);
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        // 加载本地数据
        loadLocal();
    }

    private void loadLocal() {
        // 返回为[dev]
        String activeProfiles =Arrays.toString(environment.getActiveProfiles());
        log.info("加载"+  env +"环境数据"+activeProfiles);
        ExcelReader reader;
        try {
            reader = ExcelUtil.getReader(onlineUrl+"dict.xlsx");
        } catch (Exception file) {
            reader = ExcelUtil.getReader(localUrl+"dict.xlsx");
        }
        List<Map<String, Object>> list = reader.readAll();
        for (Map<String, Object> map : list) {
            JSONObject jsonObject = JSONUtil.parseObj(map);
            biMap.put(String.valueOf(jsonObject.get("dictKey")), String.valueOf(jsonObject.get("dictValue")));
        }
        log.info("加载"+ env +"环境数据完成");
        //关闭流
        reader.close();
    }
}

