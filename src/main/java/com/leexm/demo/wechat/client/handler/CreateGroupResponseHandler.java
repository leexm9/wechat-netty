package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-16 00:46
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.println(String.format("群创建成功，id 为[%s]，群成员有:%s", msg.getGroupId(), msg.getUserNames()));
    }

}
