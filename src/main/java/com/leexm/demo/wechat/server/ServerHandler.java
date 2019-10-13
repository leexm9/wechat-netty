package com.leexm.demo.wechat.server;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.PacketCode;
import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import com.leexm.demo.wechat.protocol.request.MessageRequestPacket;
import com.leexm.demo.wechat.protocol.response.LoginResponsePacket;
import com.leexm.demo.wechat.protocol.response.MessageResponsePacket;
import com.leexm.demo.wechat.util.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

/**
 * @author leexm
 * @date 2019-10-13 20:38
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCode.getInstance().decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginPacket = (LoginRequestPacket) packet;

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setVersion(loginPacket.getVersion());
            if (valid(loginPacket)) {
                System.out.println(String.format("用户 %s 登录成功", loginPacket.getUsername()));
//                LoginUtils.markAsLogin(ctx.channel());
                responsePacket.setSuccess(true);
            } else {
                responsePacket.setSuccess(false);
                responsePacket.setReason("账号密码校验失败");
            }
            ByteBuf response = PacketCode.getInstance().encode(ctx.alloc(), responsePacket);
            ctx.channel().writeAndFlush(response);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket requestPacket = (MessageRequestPacket) packet;
            System.out.println(LocalDateTime.now() + " 收到客户端消息：" + requestPacket.getMessage());

            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setMessage(String.format("服务器端回复[%s]", requestPacket.getMessage()));
            ByteBuf response = PacketCode.getInstance().encode(ctx.alloc(), responsePacket);
            ctx.channel().writeAndFlush(response);
        }
    }

    private boolean valid(LoginRequestPacket loginPacket) {
        return true;
    }

}
