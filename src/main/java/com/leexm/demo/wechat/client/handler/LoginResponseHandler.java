package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.PacketCode;
import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import com.leexm.demo.wechat.protocol.response.LoginResponsePacket;
import com.leexm.demo.wechat.util.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author leexm
 * @date 2019-10-14 00:59
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now() + " 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        loginPacket.setUserId(UUID.randomUUID().toString());
        loginPacket.setUsername("Tom");
        loginPacket.setPassword("123456");

        ByteBuf byteBuf = PacketCode.getInstance().encode(ctx.alloc(), loginPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (valid(msg)) {
            System.out.println(LocalDateTime.now() + " 登录成功！");
            LoginUtils.markAsLogin(ctx.channel());
        } else {
            System.out.println(LocalDateTime.now() + " 登录失败，失败原因：" + msg.getReason());
        }
    }

    private boolean valid(LoginResponsePacket loginPacket) {
        return true;
    }

}
