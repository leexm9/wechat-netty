package com.leexm.demo.wechat.server.handler;


import com.leexm.demo.wechat.protocol.PacketCode;
import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import com.leexm.demo.wechat.protocol.response.LoginResponsePacket;
import com.leexm.demo.wechat.util.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-14 00:40
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(msg.getVersion());
        if (valid(msg)) {
            System.out.println(String.format("用户 %s 登录成功", msg.getUsername()));
            LoginUtils.markAsLogin(ctx.channel());
            responsePacket.setSuccess(true);
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setReason("账号密码校验失败");
        }
        ByteBuf response = PacketCode.getInstance().encode(ctx.alloc(), responsePacket);
        ctx.channel().writeAndFlush(response);
    }

    private boolean valid(LoginRequestPacket loginPacket) {
        return true;
    }

}
