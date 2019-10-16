package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.LogoutRequestPacket;
import com.leexm.demo.wechat.protocol.response.LogoutResponsePacket;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-15 23:39
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        SessionUtils.unBindSession(ctx.channel());
        LogoutResponsePacket packet = new LogoutResponsePacket();
        ctx.channel().writeAndFlush(packet);
    }

}
