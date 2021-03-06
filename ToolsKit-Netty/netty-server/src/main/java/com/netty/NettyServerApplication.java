package com.netty;

import com.netty.service.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;


/**
 * @author xq
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class NettyServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NettyServerApplication.class, args);
        NettyServer nettyServer = context.getBean(NettyServer.class);
        nettyServer.start();
    }

}
