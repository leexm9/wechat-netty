package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-16 01:27
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(String.format("加入群聊[%s]成功!", msg.getGroupId()));
        } else {
            System.out.println(String.format("加入群聊[%s]失败，原因:%s.", msg.getGroupId(), msg.getReason()));
        }
    }

}
