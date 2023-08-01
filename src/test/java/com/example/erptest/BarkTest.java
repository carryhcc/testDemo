package com.example.erptest;

import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/10/19 18:16
 */
public class BarkTest {

    @Value("${others.bark.url}")
    private String barkUrl;

    @Value("${others.bark.icoUrl}")
    private String icoUrl;
    @Test
    public void test(){
        String title = "标题";
        String msg = "/内容";
        String ico = "?icon=";
        String group = "&group=test";
        String url = barkUrl+title+msg+ico+icoUrl+group;
        String result = HttpUtil.createGet(url).execute().charset("utf-8").body();
        System.out.println(url);
        System.out.println(result);
    }
}
