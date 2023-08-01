package com.example.erptest;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.mapper.UserMapper;
import com.example.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/10 10:31
 */
@SpringBootTest
public class ExcelOutText {

    @Resource
    private UserMapper userMapper;

    @Test
    void out() {
        System.out.println("导出测试");
        List<User> rowList = userMapper.selectList(null);

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("/Users/cchu/Downloads/test.xlsx");
        //通过构造方法创建writer
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("score", "分数");
        writer.addHeaderAlias("isPass", "是否通过");
        writer.addHeaderAlias("examDate", "考试时间");
        //默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
        writer.setOnlyAlias(true);
        //合并单元格后的标题行，使用默认标题样式

        //一次性写出内容，强制输出标题
        writer.write(rowList, true);
        //关闭writer，释放内存
        writer.close();
    }
}
