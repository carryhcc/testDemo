package com.example.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.Notion;
import com.example.model.Result;
import com.example.model.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/28 16:16
 */
@Slf4j
@RestController
@RequestMapping("/notion")
public class NotionController {

    @Value("${others.notion.reboot}")
    private String reboot;
    @Value("${others.notion.pereId}")
    private String pereId;

    private UserService userService;

    @PostMapping("/addTest")
    public String addTest(@RequestBody Notion notion) {
        String json = "{\"children\":[{\"object\":\"block\",\"type\":\"heading_2\",\"heading_2\":{\"text\":[{\"type\":\"text\",\"text\":{\"content\":\"" + notion.getTitle() + "\"}}]}},{\"object\":\"block\",\"type\":\"paragraph\",\"paragraph\":{\"text\":[{\"type\":\"text\",\"text\":{\"content\":\"" + notion.getMsg() + "\"}}]}}]}";
        String url = "https://api.notion.com/v1/blocks/" + pereId + "/children";
        String result = HttpRequest.patch(url)
                .header("Content-Type", "application/json")
                .header("Authorization", reboot)
                .header("Notion-Version", "2021-05-13")
                .body(json)
                .execute().body();
//        System.out.print(JSONUtil.parseObj(result).toStringPretty());
        return JSONUtil.parseObj(result).toStringPretty();
    }

    @PostMapping("/test")
    public Result test(@RequestBody Notion notion) {
//        System.out.println(notion);
//        ArrayList<String> objects = new ArrayList<>();
        BaseMapper<User> baseMapper = userService.getBaseMapper();
        log.info("error:{}", baseMapper);
        return Result.success(notion);
    }
}
