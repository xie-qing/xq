package handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/9/5 09:49
 */
public class NettyClientCon {


    private static final EventLoopGroup group = new NioEventLoopGroup();

    private ChannelFuture channelFuture;

    public ChannelFuture start() {
        try {
            Bootstrap client = new Bootstrap();
            client.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyClientInitializer());
            // 启动客户端
            channelFuture = client.connect("127.0.0.1", 18000).sync();

            chat(channelFuture);

            // 等待客户端链路关闭
            channelFuture.channel().close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            group.shutdownGracefully();
        }
        return null;
    }

    public void close() {
        if (channelFuture.isSuccess()) {
            channelFuture.channel().close();
        }
        group.shutdownGracefully();
    }

    private static void chat(ChannelFuture channelFuture) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("请开始输入：");
        while ((userInput = reader.readLine()) != null) {
            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            }
            channelFuture.channel().writeAndFlush(userInput);
        }
    }
}
