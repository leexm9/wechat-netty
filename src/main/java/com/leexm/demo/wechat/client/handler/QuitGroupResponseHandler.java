package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author leexm
 * @date 2019-10-16 22:52
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            String message = msg.getType() == 0 ? String.format("退出群聊[%s]", msg.getGroupId()) : msg.getMessage();
            System.out.println(message);
        } else {
            System.out.println(String.format("退出群聊[%s]失败", msg.getGroupId()));
        }
    }
}
