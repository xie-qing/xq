package com.tools;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tools.user.mapper")
//@NacosPropertySource(dataId = "nacos-tools-kit-dev" , autoRefreshed = true)
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ToolsKitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsKitApplication.class, args);
	}

}
