package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.JoinGroupRequestPacket;
import com.leexm.demo.wechat.protocol.response.JoinGroupResponsePacket;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author leexm
 * @date 2019-10-16 01:17
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        boolean flag = false;
        if (channelGroup != null) {
            channelGroup.add(ctx.channel());
            flag = true;
        }

        // 创建返回消息
        JoinGroupResponsePacket packet = new JoinGroupResponsePacket();
        packet.setGroupId(msg.getGroupId());
        packet.setSuccess(flag);
        if (!flag) {
            packet.setReason("群聊不存在!");
        }
        ctx.channel().writeAndFlush(packet);
    }

}
