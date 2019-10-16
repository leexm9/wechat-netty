package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.ListGroupMembersRequestPacket;
import com.leexm.demo.wechat.protocol.response.ListGroupMembersResponsePacket;
import com.leexm.demo.wechat.session.Session;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leexm
 * @date 2019-10-16 01:15
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) throws Exception {
        // 创建响应 packet
        ListGroupMembersResponsePacket packet = new ListGroupMembersResponsePacket();
        String groupId = msg.getGroupId();
        packet.setGroupId(groupId);
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        boolean flag = channelGroup != null;
        if (flag) {
            List<Session> sessions =
                    channelGroup.stream().map(channel -> SessionUtils.getSession(channel)).collect(Collectors.toList());
            packet.setSessions(sessions);
        } else {
            packet.setReason("群聊不存在!");
        }
        packet.setSuccess(flag);
        ctx.channel().writeAndFlush(packet);
    }

}
