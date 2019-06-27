package com.tools.netty.service;

import com.tools.netty.config.NettyConfigProperties;
import com.tools.netty.handler.NettyServerFilter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author admin3
 */
@Service
@Slf4j
public class NettyServer {
    // 通过nio方式来接收连接和处理连接
    private static EventLoopGroup boos = new NioEventLoopGroup();
    private static EventLoopGroup works = new NioEventLoopGroup();
    private static ServerBootstrap serverBootstrap = new ServerBootstrap();

    @Autowired
    NettyConfigProperties nettyConfigProperties;

    @Autowired
    NettyServerFilter nettyServerFilter;

    public void start() {
        try {
            serverBootstrap.group(boos,works);
            serverBootstrap.channel(NioServerSocketChannel.class);
            //设置过滤器
            serverBootstrap.childHandler(nettyServerFilter);
            // 服务器绑定端口监听
            ChannelFuture channelFuture = serverBootstrap.bind(nettyConfigProperties.getPort()).sync();
            log.info("\n 服务器监听端口为： " + nettyConfigProperties.getPort());
            // 监听服务器关闭监听
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
            log.info("\n连接失败" + e.getMessage());
        }finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            works.shutdownGracefully();
            boos.shutdownGracefully();
        }

    }


}
