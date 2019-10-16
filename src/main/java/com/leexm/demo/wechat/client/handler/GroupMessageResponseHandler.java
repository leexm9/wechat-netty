package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-16 23:52
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        System.out.println(String.format("收到群[%s]成员[%s]发送的消息:%s", msg.getGroupId(),
                msg.getFromUser().getUserName(), msg.getMessage()));
    }

}
