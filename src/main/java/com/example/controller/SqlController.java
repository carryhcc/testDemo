package com.example.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UserMapper;
import com.example.model.Result;
import com.example.model.User;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/22 17:23
 */
@Slf4j
@RestController
@RequestMapping("/sql")
public class SqlController {
    @Resource
    public UserService userService;
    @Resource
    public UserMapper userMapper;

    @GetMapping("/select")
    @DS("oracle")
    public Result select() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(User::getName, "胡");
//        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        Page<User> userPage = new Page<>(1, 10);
        IPage<User> userIPage = userMapper.selectPage(userPage, userLambdaQueryWrapper);
        log.info("总页数:{}", userIPage.getPages());
        log.info("总记录数:{}", userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
        log.info("userIPage:{}", userIPage);
        return Result.success(userIPage);
    }

    @GetMapping("/update")
    @DS("oracle")
    public Result update() {
        List<User> list = userService.list();
        for (User user : list) {
            user.setId(IdWorker.getId());
        }
        return Result.success();
    }
}
