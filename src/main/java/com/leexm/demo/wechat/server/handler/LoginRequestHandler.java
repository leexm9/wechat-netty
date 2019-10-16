package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import com.leexm.demo.wechat.protocol.response.LoginResponsePacket;
import com.leexm.demo.wechat.session.Session;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author leexm
 * @date 2019-10-14 00:40
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(msg.getVersion());
        responsePacket.setUserName(msg.getUsername());
        if (valid(msg)) {
            System.out.println(String.format("用户[%s]登录成功", msg.getUsername()));
            SessionUtils.markAsLogin(ctx.channel());

            responsePacket.setSuccess(true);
            String userId = "" + ThreadLocalRandom.current().nextInt(1000);
            responsePacket.setUserId(userId);
            // 保存会话
            SessionUtils.bindSession(new Session(userId, msg.getUsername()), ctx.channel());
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setReason("账号密码校验失败");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtils.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginPacket) {
        return true;
    }

}
