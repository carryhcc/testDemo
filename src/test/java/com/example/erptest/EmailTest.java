package com.example.erptest;

import cn.hutool.extra.mail.MailUtil;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/14 15:11
 */

public class EmailTest {
    @Test
    public void mailUp() {
        System.out.println("开始邮件发送！");
        try {
            MailUtil.send("test@qq.com", "测试", "邮件来自Hutool工具类测试", false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
