package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.GroupMessageRequestPacket;
import com.leexm.demo.wechat.protocol.response.GroupMessageResponsePacket;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author leexm
 * @date 2019-10-16 23:47
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);

        GroupMessageResponsePacket packet = new GroupMessageResponsePacket();
        packet.setGroupId(groupId);
        packet.setFromUser(SessionUtils.getSession(ctx.channel()));
        packet.setMessage(msg.getMessage());

        channelGroup.writeAndFlush(packet);
    }

}
