package handler;

import com.netty.config.NettyConfigProperties;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private static final EventLoopGroup group = new NioEventLoopGroup();
    public static void main(String[] args) {
        try {
            Bootstrap client = new Bootstrap();
            client.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyClientInitializer());
            // 启动客户端
            ChannelFuture channelFuture = client.connect("127.0.0.1", 18000).sync();


            chat(channelFuture);

            // 等待客户端链路关闭
            channelFuture.channel().close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            group.shutdownGracefully();
        }
    }

    private static void chat(ChannelFuture channelFuture) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("请开始输入：");
        while ((userInput = reader.readLine()) != null) {
            if ("exit".equalsIgnoreCase(userInput)){
                break;
            }
            channelFuture.channel().writeAndFlush(userInput);
        }
    }
}
