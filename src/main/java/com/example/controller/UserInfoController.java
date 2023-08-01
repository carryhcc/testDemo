package com.example.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UserInfoMapper;
import com.example.model.DTO.LimitDTO;
import com.example.model.DTO.UserInfoDTO;
import com.example.model.Result;
import com.example.model.UserInfo;
import com.example.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/11 11:41
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Resource
    public UserInfoService userInfoService;
    @Resource
    public UserInfoMapper userInfoMapper;

    @PostMapping ("/select")
    @DS("oracle")
    public Result userInfo(@RequestBody UserInfoDTO dto) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        if(!dto.getName().isEmpty()){
            queryWrapper.like("name",dto.getName());
        }
        PageHelper.startPage(dto.getPage(),dto.getSize());
        List<UserInfo> userIPage = userInfoMapper.selectList(queryWrapper);
        PageInfo<UserInfo> userInfoPageInfo = new PageInfo<>(userIPage);
        return Result.success(userInfoPageInfo);
    }
    @GetMapping("/select2")
    public Result userList() {
        System.out.println(("----- selectAll method test ------"));
        List<UserInfo> userList = userInfoMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
        return Result.success(userList);
    }
}
