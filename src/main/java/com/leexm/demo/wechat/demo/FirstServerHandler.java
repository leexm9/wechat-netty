package com.leexm.demo.wechat.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author leexm
 * @date 2019-10-13 11:55
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = "Welcome to the world!".getBytes(Charset.forName("UTF-8"));
        byteBuf.writeBytes(bytes);

        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取数据
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(LocalDateTime.now() + "：服务器读取到数据 --> " + byteBuf.toString(Charset.forName("UTF-8")));

        // 回复客户端
        System.out.println(LocalDateTime.now() + "：服务端写出数据.");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = "Pong:hello, world".getBytes(Charset.forName("UTF-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
