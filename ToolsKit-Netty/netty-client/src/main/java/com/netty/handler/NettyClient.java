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


    public void start() {
        ChannelFuture f = null;
        boolean initFalg = true;
        try {
            if (bootstrap != null) {
                bootstrap.group(boos);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootstrap.handler(nettyClientInitializer);
                bootstrap.remoteAddress(nettyConfigProperties.getUrl(), nettyConfigProperties.getPort());
                f = bootstrap.connect().addListener((ChannelFuture futureListener) -> {
                    final EventLoop eventLoop = futureListener.channel().eventLoop();
                    if (!futureListener.isSuccess()) {
                        System.out.println("与服务端断开连接!在10s之后准备尝试重连!");
                        eventLoop.schedule(this::start, 10, TimeUnit.SECONDS);
                    }
                });
                if(initFalg){
                    System.out.println("Netty客户端启动成功!");
                    initFalg=false;
                }
                //向服务端发送信息
                f.channel().writeAndFlush("dsah");
                // 阻塞
                f.channel().closeFuture().sync();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端连接失败!"+e.getMessage());
        }

    }

}
