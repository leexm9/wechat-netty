package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.ListGroupMembersResponsePacket;
import com.leexm.demo.wechat.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leexm
 * @date 2019-10-16 01:50
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket msg) throws Exception {
        if (!msg.isSuccess()) {
            System.out.println(String.format("获取群成员失败，失败原因:%s", msg.getReason()));
        } else {
            List<Session> sessions = msg.getSessions();
            List<String> namses = sessions.stream().map(Session::getUserName).collect(Collectors.toList());
            System.out.println(String.format("群[%s]成员包括:%s", msg.getGroupId(), namses));
        }
    }
}
