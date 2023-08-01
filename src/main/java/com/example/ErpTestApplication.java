package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * @author cchu
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.mapper")
public class ErpTestApplication implements ApplicationRunner {

    @Value("${server.port}")
    Integer port;

    @Value("${server.env}")
    String env;

    public static void main(String[] args) {
        SpringApplication.run(ErpTestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "7890");
        System.out.println("---------------------------------");
        System.out.println("  🚀启动成功!!!  环境为"+env);
        System.out.println("本地访问地址：http://localhost:" + port);
        System.out.println("外部访问地址：http://你的服务器IP:" + port);
        System.out.println("---------------------------------");
    }
}
