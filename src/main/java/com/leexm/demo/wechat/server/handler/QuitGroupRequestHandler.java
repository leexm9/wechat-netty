package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.QuitGroupRequestPacket;
import com.leexm.demo.wechat.protocol.response.QuitGroupResponsePacket;
import com.leexm.demo.wechat.session.Session;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author leexm
 * @date 2019-10-16 22:42
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        Session session = SessionUtils.getSession(ctx.channel());
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        if (channelGroup != null) {
            channelGroup.remove(ctx.channel());
            if (channelGroup.isEmpty()) {
                SessionUtils.removeChannelGroup(groupId);
            } else {
                QuitGroupResponsePacket packet = new QuitGroupResponsePacket();
                packet.setSuccess(true);
                packet.setType(1);
                packet.setMessage(String.format("%s退出群聊[%s]", session.getUserName(), groupId));
                channelGroup.writeAndFlush(packet);
            }
        }

        QuitGroupResponsePacket packet = new QuitGroupResponsePacket();
        packet.setSuccess(true);
        packet.setGroupId(groupId);
        packet.setType(0);
        ctx.channel().writeAndFlush(packet);
    }
}
