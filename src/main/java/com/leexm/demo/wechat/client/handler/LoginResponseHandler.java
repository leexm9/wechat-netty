package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.LoginResponsePacket;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author leexm
 * @date 2019-10-14 00:59
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        String userId = msg.getUserId();
        String userName = msg.getUserName();
        if (valid(msg)) {
            System.out.println(String.format("%s [%s]登录成功，userId:%s", LocalDateTime.now(), userName, userId));
            SessionUtils.markAsLogin(ctx.channel());
        } else {
            System.out.println(String.format("%s:[%s]登录失败，失败原因:%s", LocalDateTime.now(), userName, msg.getReason()));
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
    }

    private boolean valid(LoginResponsePacket loginPacket) {
        return true;
    }

}
