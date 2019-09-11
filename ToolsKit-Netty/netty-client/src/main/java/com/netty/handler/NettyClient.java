package com.netty.handler;

import com.netty.config.NettyConfigProperties;
import com.netty.service.SendMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/8/28 16:00
 */
@Slf4j
@Component
public class NettyClient {

    // 通过nio方式来接收连接和处理连接
    private static EventLoopGroup boos = new NioEventLoopGroup();
    private static EventLoopGroup works = new NioEventLoopGroup();
    private static Bootstrap bootstrap = new Bootstrap();


    @Autowired
    NettyConfigProperties nettyConfigProperties;

    @Resource
    NettyClientInitializer nettyClientInitializer;

    @Resource
    SendMsg sendMsg;

    ChannelFuture channelFuture;


    public void start() {
        try {
            bootstrap.group(works)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyClientInitializer());
            // 启动客户端
            channelFuture = bootstrap.connect("127.0.0.1", 18000).sync();
            chat(channelFuture);
            // 等待客户端链路关闭
            channelFuture.channel().close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            works.shutdownGracefully();
        }


    }

    /**
     * 〈〉
     * 功能描述: 关闭客户端链路<br>
     * 〈/〉
     * @param
     * @return
     * @author XQ
     * @date 2019/8/30 11:13
     */
    public void close(){

    }

    private void chat(ChannelFuture channelFuture) {

    }
}
