package com.spirngBoot.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.*;

/**
 * springBootAdmin启动类
 * @author cchu
 */
@EnableAdminServer
@SpringBootApplication
public class SpringBootAdminApplication  implements ApplicationRunner {

	@Value("${server.port}")
	Integer port;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminApplication.class, args);
	}
	@Override
	public void run(ApplicationArguments args) {
		out.println("  🚀springBootAdmin 启动成功!!!");
		out.println("本地访问地址：http://localhost:" + port);
	}

}
