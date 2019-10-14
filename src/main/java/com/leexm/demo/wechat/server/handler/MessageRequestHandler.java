package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.request.MessageRequestPacket;
import com.leexm.demo.wechat.protocol.response.MessageResponsePacket;
import com.leexm.demo.wechat.session.Session;
import com.leexm.demo.wechat.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-14 00:45
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 拿到消息发送方的会话
        Session session = SessionUtils.getSession(ctx.channel());

        // 构造要发送的消息
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUserName(session.getUserName());
        responsePacket.setMessage(msg.getMessage());

        // 拿到消息接受方的 channel
        Channel toChannel = SessionUtils.getChannel(msg.getToUserId());

        // 发送消息给接受方
        if (toChannel != null && SessionUtils.hasLogin(toChannel)) {
            toChannel.writeAndFlush(responsePacket);
        } else {
            System.out.println("%s [%s]不在线，消息发送失败");
        }
    }

}
