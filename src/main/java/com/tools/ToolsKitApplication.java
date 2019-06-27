package com.tools;



import com.tools.netty.service.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.tools.user.mapper")
//@NacosPropertySource(dataId = "nacos-tools-kit-dev" , autoRefreshed = true)
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ToolsKitApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ToolsKitApplication.class, args);
		NettyServer nettyServer = context.getBean(NettyServer.class);
		nettyServer.start();
	}

}
