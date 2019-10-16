package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-17 00:04
 */
public class LogoutResponseHander extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        System.out.println("退出登录");
    }

}
