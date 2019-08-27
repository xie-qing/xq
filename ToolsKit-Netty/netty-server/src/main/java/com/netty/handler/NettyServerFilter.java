package com.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author admin3
 */
@Slf4j
@Component
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChanne) throws Exception {
        log.info("初始化服务端。。。。。。");
        //添加编解码
        socketChanne.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        socketChanne.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        socketChanne.pipeline().addLast(new TextWebSocketFrameHandler());
//        ChannelPipeline ph = ch.pipeline();
        //====================以上是使用支持http协议====
        //===================增加心跳===================
        //如果是读写空闲  不处理
        //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
//        ph.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
        // 解码和编码，应和客户端一致
        //传输的协议 Protobuf
//        ph.addLast(new ProtobufVarint32FrameDecoder());
//        ph.addLast(new ProtobufDecoder());
//        ph.addLast(new ProtobufVarint32LengthFieldPrepender());
//        ph.addLast(new ProtobufEncoder());
        //业务逻辑实现类
//        ph.addLast("textWebSocketFrameHandler", nettyServerHandler);
        //websocket 基于http协议 所以要有http编解码器
//        ph.addLast(new HttpServerCodec());
        //对写大数据流的支持
//        ph.addLast(new ChunkedWriteHandler());
        //对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        //几乎在netty中的编程 ，都会使用到此handler
//        ph.addLast(new HttpObjectAggregator(1024*64));
        /*
         * websocket 服务器处理的协议 ，用于指定给客户端连接访问的路由 :/ws
         * 本handler 会帮你处理一些繁重的复杂的事
         * 会帮你处理握手动作 ：handshaking （close，ping，pong）ping+pong=心跳
         * 对于websocket来讲， 都是以frams进行传输的不同的数据类型对应的frames也不同
         * */
//        ph.addLast(new WebSocketServerProtocolHandler("/ws"));
    }
}
