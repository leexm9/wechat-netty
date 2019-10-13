package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.protocol.PacketCode;
import com.leexm.demo.wechat.protocol.request.MessageRequestPacket;
import com.leexm.demo.wechat.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author leexm
 * @date 2019-10-14 00:45
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println(LocalDateTime.now() + " 收到客户端消息：" + msg.getMessage());

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage(String.format("服务器端回复[%s]", msg.getMessage()));
        ByteBuf response = PacketCode.getInstance().encode(ctx.alloc(), responsePacket);
        ctx.channel().writeAndFlush(response);
    }

}
