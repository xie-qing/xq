package com.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author admin3
 */
@Slf4j
@Component
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {

    @Resource
    TextWebSocketFrameHandler textWebSocketFrameHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        log.info("初始化服务端。。。。。。");
        //添加编解码
        socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast(textWebSocketFrameHandler);
        //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
        socketChannel.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
        // 解码和编码，应和客户端一致

    }
}
