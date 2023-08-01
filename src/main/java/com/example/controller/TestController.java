package com.example.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.cglib.CglibUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.ChatMsg;
import com.example.model.DTO.ChatMsgDTO;
import com.example.model.Result;
import com.example.model.annotation.MethodLog;
import com.example.service.ChatMsgService;
import com.example.service.WxRebootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/2/9 10:28
 */
@RefreshScope
@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    @Value("${server.env}")
    String env;

    @Autowired
    private ChatMsgService chatMsgService;

    @Autowired
    public WxRebootService wxRebootService;

    @MethodLog
    @PostMapping("/ring")
    // 缓存的key为id 缓存的条件为 当前方法参数组成的数组 查询条数不为 0
    @Cacheable(value = "chat_msg_cache", key = "#root.args", unless = "#result.data.size == 0 ")
    public Result ring(@RequestBody ChatMsgDTO dto) {
        log.warn("环境为" + env);
        Page<ChatMsg> page = new Page<>(dto.getPageNumber(), dto.getPageSize());
        QueryWrapper<ChatMsg> wrapper = new QueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(dto.getId()), "id", dto.getId())
                .eq(ObjectUtil.isNotEmpty(dto.getIp()), "ip", dto.getIp())
                .ge(ObjectUtil.isNotEmpty(dto.getStartTime()), "add_time", dto.getStartTime())
                .le(ObjectUtil.isNotEmpty(dto.getEndTime()), "add_time", dto.getEndTime())
                .orderByDesc("add_time")
                .select("id", "question", "answer");
        Page<ChatMsg> pageListInfo = chatMsgService.page(page, wrapper);
        // 彩虹计数 [1,2,3] 参数意义分别为：当前页、总页数、每屏展示的页数
        int[] rainbow = PageUtil.rainbow((int) pageListInfo.getCurrent(), (int) pageListInfo.getPages(), (int) pageListInfo.getSize());
        pageListInfo.setCountId(Arrays.toString(rainbow));
        return Result.success(pageListInfo);
    }

    @MethodLog
    @PostMapping("/copy")
    public Result copy(@RequestBody ChatMsgDTO dto) {
        // CGLib (Code Generation Library) 是一个强大的,高性能,高质量的Code生成类库，通过此库可以完成动态代理、Bean拷贝等操作。
        ChatMsg chatMsg = CglibUtil.copy(dto, ChatMsg.class);
        wxRebootService.moFish();
        return Result.success(chatMsg);
    }

    @MethodLog
    @GetMapping("/py")
    public Result py() {
        //请求列表页
        for (int i = 0; i < 5; i++) {
            String listContent = HttpUtil.get("https://www.oschina.net/action/ajax/get_more_news_list?newsType=&p=" + i);
            ArrayList<ChatMsg> chatList = new ArrayList<>();
            //使用正则获取所有标题
            List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
            for (String title : titles) {
                ChatMsg chatMsg = new ChatMsg("开源中国", title);
                chatList.add(chatMsg);
                Console.log(title);
            }
            chatMsgService.saveBatch(chatList);
        }
        return Result.success();
    }
}

