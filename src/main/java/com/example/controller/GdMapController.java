package com.example.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/6/9 17:58
 */
@Slf4j
@RestController
@RequestMapping("/map")
public class GdMapController {

    @Value("${others.gdMap.key}")
    private String key;

    //行政区域查询
    //https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
    @GetMapping("/city")
    public void city() {
        String json = "https://restapi.amap.com/v3/config/district?parameters";
        String keywords ="安陆";
        String result = HttpRequest.get(json+"&key="+key+"&keywords="+keywords)
                .execute().body();
        System.out.println(JSONUtil.parseObj(result).toStringPretty());
    }
    //天气查询
    //https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=<用户key>
    @GetMapping("/weather")
    public void weather() {
        String json = "https://restapi.amap.com/v3/weather/weatherInfo?";
        String city ="420982";
        String result = HttpRequest.get(json+"&key="+key+"&city="+city)
                .execute().body();
        System.out.println(JSONUtil.parseObj(result).toStringPretty());
    }
    //IP定位
    //https://restapi.amap.com/v3/ip?ip=114.247.50.2&output=xml&key=<用户的key>
    @GetMapping("/ip")
    public void ip() {
        String json = "https://restapi.amap.com/v3/ip?";
        String ip ="119.36.243.2";
        String result = HttpRequest.get(json+"&key="+key+"&ip="+ip+"&output=xml")
                .execute().body();
        System.out.println(JSONUtil.parseObj(result).toStringPretty());
    }
}