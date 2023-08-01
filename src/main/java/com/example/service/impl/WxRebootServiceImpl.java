package com.example.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.model.wxRtboot.Articles;
import com.example.service.WxRebootService;
import com.example.util.Base64Util;
import com.example.util.Tools;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/17 14:12
 */
@Log4j2
@Service
public class WxRebootServiceImpl implements WxRebootService {

    @Value("${others.wxReboot.key}")
    private String key;

    @Value("${others.wxReboot.url}")
    private String url;

    @Value("${others.wxReboot.weather}")
    private String weatherUrl;

    @Value("${others.dict.localUrl}")
    private String localUrl;

    @Value("${others.dict.onlineUrl}")
    private String onlineUrl;
    @SneakyThrows
    @Override
    public void moFish() {
        String success = HttpUtil.get("https://api.vvhan.com/api/moyu?type=json");
        boolean successType = Boolean.parseBoolean(JSONUtil.parseObj(success).getStr("success"));
        if (!successType) {
            log.error("下载失败！！！");
            return;
        }
        String downloadUrl = JSONUtil.parseObj(success).getStr("url");

        File file = new File(onlineUrl);
        String photoUrl;
        if (file.exists()) {
             photoUrl = onlineUrl + "moFishPic/moFish.png";
            log.info("路径为线上路径");
        } else {
            photoUrl = localUrl + "moFishPic/moFish.png";
            log.info("路径为本地路径");
        }
        long size = HttpUtil.downloadFile(downloadUrl, FileUtil.file(photoUrl), 5000);
        System.out.println("Download file size: " + size);
        byte[] imgData = FileUtil.readBytes(photoUrl);
        String base64 = Base64Util.encode(imgData);
        String md5Hex = DigestUtil.md5Hex(imgData);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgtype", "image");
        HashMap<String, String> content = new HashMap<>();
        content.put("base64", base64);
        content.put("md5", md5Hex);
        map.put("image", content);
        String body = JSONUtil.toJsonStr(map);
        String result = HttpRequest.post(url + key)
                .body(body)
                .execute().body();
        imgData.clone();
        FileUtil.del(photoUrl);
        System.out.println(result);
    }

    @Override
    public void runText(String msg) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgtype", "text");
        HashMap<String, Object> content = new HashMap<>();
        content.put("content", msg);
        String[] arr = {"@all"};
        content.put("mentioned_list", arr);
        map.put("text", content);
        run(map);
    }

    @Override
    public void runLogTimeMsg() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgtype", "news");
        HashMap<String, Object> news = new HashMap<>();
        ArrayList<Articles> articlesList = new ArrayList<>();
        Articles articles = new Articles();
        articles.setTitle("logTime");
        articles.setDescription("每日填写logTime！！！");
        articles.setUrl("https://jira.yyjzt.com/secure/Tempo.jspa#/reports/report/7f70b14d-f568-40e2-92bf-73de3f50a6d6?columns=WORKED_COLUMN&dateDisplayType=days&from=2022-05-01&groupBy=worker&periodType=CURRENT_PERIOD&subPeriodType=MONTH&teamId=14&to=2022-05-31&viewType=TIMESHEET");
        articles.setPicurl("https://img.jk.com/home/home_icon.png");
        articlesList.add(articles);
        news.put("articles", articlesList);
        map.put("news", news);
        run(map);
    }

    /**
     * 调用接口
     */
    void run(HashMap<String, Object> map) {
        if (Tools.runWeekend(new Date())) {
            return;
        }
        String body = JSONUtil.toJsonStr(map);
        HttpRequest.post(url + key)
                .body(body)
                .execute().body();
        System.out.println("执行成功！！！");
    }

}
