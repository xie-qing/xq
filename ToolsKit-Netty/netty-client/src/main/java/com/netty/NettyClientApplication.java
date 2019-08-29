package com.netty;

import com.netty.handler.NettyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/8/28 15:58
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class NettyClientApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NettyClientApplication.class, args);
        NettyClient nettyServer = context.getBean(NettyClient.class);
        nettyServer.start();
    }
}
