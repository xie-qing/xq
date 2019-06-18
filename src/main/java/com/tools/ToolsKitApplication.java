package com.tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.tools.yt.mapper")
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ToolsKitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsKitApplication.class, args);
	}

}
