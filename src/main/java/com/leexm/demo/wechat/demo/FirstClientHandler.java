package com.leexm.demo.wechat.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author leexm
 * @date 2019-10-13 11:45
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now() + "：客户端写数据");
        ByteBuf byteBuf = getByteBuf(ctx);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取数据
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(LocalDateTime.now() + "：客户端读取到数据 --> " + byteBuf.toString(Charset.forName("UTF-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = "Hello,world!".getBytes(Charset.forName("UTF-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }


}
