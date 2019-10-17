package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.request.HeartBeatRequestPacket;
import com.leexm.demo.wechat.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 客户端心跳检测
 *
 * @author leexm
 * @date 2019-10-17 01:24
 */
public class HeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                ctx.channel().writeAndFlush(new HeartBeatRequestPacket());
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket msg) throws Exception {
    }
}
