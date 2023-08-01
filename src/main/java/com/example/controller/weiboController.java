package com.example.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.mapper.WeiboInfoMapper;
import com.example.model.WeiboInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/4/22 23:08
 */
@Slf4j
@RestController
public class weiboController {

    @Resource
    private WeiboInfoMapper mapper;

    @PostMapping("/phoneSelectWeibo")
    @DS("mysql")
    public String phoneSelectWeibo(@RequestParam String phone) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phone);
        String result = HttpUtil.get("https://zy.xywlapi.cc/wbphone", paramMap);
        log.info(result);
        WeiboInfo change = change(result);
        return change.getStatus() == 200 ? change.getWeiboHome() : "没有找到";
    }

    @PostMapping("/weiboSelectPhone")
    @DS("mysql")
    public String weiboSelectPhone(@RequestParam String uid) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", uid);
        String result = HttpUtil.get("https://zy.xywlapi.cc/wbapi", paramMap);
        log.info(result);
        WeiboInfo change = change(result);
        return change.getStatus() == 200 ? change.getPhone() : "没有找到";
    }

    WeiboInfo change(String result) {
        JSONObject json = JSON.parseObject(result);

        WeiboInfo weiboInfo = new WeiboInfo();
        Integer status = Integer.valueOf(json.getString("status"));
        String message = json.getString("message");
        String phone = json.getString("phone");
        String uid = json.getString("id");
        weiboInfo.setPhone(phone);
        weiboInfo.setUid(uid);
        weiboInfo.setPhoneCity(json.getString("phonediqu"));
        weiboInfo.setWeiboHome("https://weibo.com/u/" + uid);
        weiboInfo.setStatus(status);
        weiboInfo.setMessage(message);
        mapper.insert(weiboInfo);
        return weiboInfo;
    }
}
