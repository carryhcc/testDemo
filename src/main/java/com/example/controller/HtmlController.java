package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/23 14:39
 */
@Slf4j
@Controller
public class HtmlController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping("/weibo")
    public ModelAndView weibo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weibo");
        return modelAndView;
    }
    @RequestMapping("/emoji")
    public ModelAndView emoji(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("emoji");
        return modelAndView;
    }
}
