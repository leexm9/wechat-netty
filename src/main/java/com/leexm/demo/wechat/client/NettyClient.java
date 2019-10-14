package com.leexm.demo.wechat.client;

import com.leexm.demo.wechat.client.handler.LoginResponseHandler;
import com.leexm.demo.wechat.client.handler.MessageResponseHandler;
import com.leexm.demo.wechat.protocol.PacketCode;
import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import com.leexm.demo.wechat.protocol.request.MessageRequestPacket;
import com.leexm.demo.wechat.codec.PacketDecoder;
import com.leexm.demo.wechat.codec.PacketEncoder;
import com.leexm.demo.wechat.codec.Spliter;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author leexm
 * @date 2019-10-13 20:28
 */
public class NettyClient {

    private static final int PORT = 8088;

    private static final String HOST = "127.0.0.1";

    /** 最大可重连次数 */
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    /**
     * 可重试连接服务器
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private static void connect(final Bootstrap bootstrap, final String host, final int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.println("重试次数已经用完，放弃连接！");
            } else {
                // 第几次连接
                int order = MAX_RETRY - retry + 1;
                // 本次重连时间间隔
                int delay = 1 << order;
                System.out.println(LocalDateTime.now() + "：连接失败，第" + order + "次重连....");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay,
                    TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtils.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    // 创建登录对象
                    LoginRequestPacket loginPacket = new LoginRequestPacket();
                    String userName = scanner.nextLine();
                    loginPacket.setUsername(userName);
                    loginPacket.setPassword("12245");
                    channel.writeAndFlush(loginPacket);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String text = scanner.nextLine();
                    int index = StringUtils.indexOf(text," ");
                    channel.writeAndFlush(new MessageRequestPacket(StringUtils.substring(text,0, index),
                            StringUtils.substring(text, index + 1)));
                }
            }
        }).start();
    }

}
