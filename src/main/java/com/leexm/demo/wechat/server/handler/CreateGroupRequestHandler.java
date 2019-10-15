package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.CreateGroupRequestPacket;
import com.leexm.demo.wechat.protocol.response.CreateGroupResponsePacket;
import com.leexm.demo.wechat.session.Session;
import com.leexm.demo.wechat.util.IdUtils;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leexm
 * @date 2019-10-15 23:47
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIds = msg.getUserIds();
        List<String> userNames = new ArrayList<>();

        // 创建一个 channelGroup
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        // 加入自身
        channelGroup.add(ctx.channel());
        userNames.add(SessionUtils.getSession(ctx.channel()).getUserName());
        // 加入其他人
        userIds.stream().forEach(userId -> {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNames.add(SessionUtils.getSession(channel).getUserName());
            }
        });

        // 群聊响应
        String groupId = IdUtils.randomId();
        CreateGroupResponsePacket packet = new CreateGroupResponsePacket();
        packet.setUserNames(userNames);
        packet.setSuccess(true);
        packet.setGroupId(groupId);

        // 给每个客户端发送建群通知
        channelGroup.writeAndFlush(packet);
        System.out.println(String.format("群创建成功，id 为[%s]，群成员有:%s", groupId, userNames));

        SessionUtils.bindChannelGroup(groupId, channelGroup);
    }

}
