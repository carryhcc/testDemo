package com.example.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.SyncMsgMapper;
import com.example.mapper.UserConfigMapper;
import com.example.model.Result;
import com.example.model.UserConfig;
import com.example.service.AsyncService;
import com.example.service.UserConfigService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/2/18 16:01
 */
@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Resource
    private UserConfigService userConfigService;
    @Resource
    private UserConfigMapper userConfigMapper;
    @Resource
    private SyncMsgMapper syncMsgMapper;
    @Resource
    private AsyncService asyncService;
    /**
     * pageHelper分页
     */
//    public Result pageHelperList(){
//        PageHelper.startPage(1, 1);
//        QueryWrapper<UserConfig> userConfigQueryMapper = new QueryWrapper<>();
//        userConfigQueryMapper.eq("id",1).eq("role","user");
//        List<UserConfig> userConfigs = userConfigMapper.selectList(userConfigQueryMapper);
//        PageInfo pageInfo = new PageInfo(userConfigs);
//        System.out.println("返回结果："+pageInfo);
//        return  Result.success(pageInfo);
//    }
    @GetMapping({"daochu", "fenye"})
    @DS("oracle")
    /**
     * pMybatisPlus分页
     */
    public Result MybatisPlusList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        IPage<UserConfig> page = new Page<>(pageNum,pageSize,false);
        QueryWrapper<UserConfig> userConfigQueryMapper = new QueryWrapper<>();
        userConfigQueryMapper.eq("role","user");
        userConfigMapper.selectPage(page, userConfigQueryMapper);
        System.out.println("返回结果："+page);
        System.out.println("返回数量："+page.getTotal());
        page.getRecords().forEach(System.out::println);
        System.out.println("返回结果："+page.getRecords());
        return  Result.success();
    }
    @GetMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }
}
