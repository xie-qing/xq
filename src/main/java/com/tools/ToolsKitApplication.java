package com.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ToolsKitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsKitApplication.class, args);
	}

}
