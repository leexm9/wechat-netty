package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import com.leexm.demo.wechat.protocol.PacketCode;
import com.leexm.demo.wechat.protocol.response.LoginResponsePacket;
import com.leexm.demo.wechat.protocol.response.MessageResponsePacket;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author leexm
 * @date 2019-10-13 20:31
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCode.getInstance().decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket responsePacket = (LoginResponsePacket) packet;
            if (responsePacket.isSuccess()) {
                System.out.println(LocalDateTime.now() + " 登录成功！");
                SessionUtils.markAsLogin(ctx.channel());
            } else {
                System.out.println(LocalDateTime.now() + " 登录失败，失败原因：" + responsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket responsePacket = (MessageResponsePacket) packet;
            System.out.println(LocalDateTime.now() + " 收到服务器端的消息：" + responsePacket.getMessage());
        }
    }
}
