package com.leexm.demo.wechat.client.handler;

import com.leexm.demo.wechat.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author leexm
 * @date 2019-10-14 01:01
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        String userId = msg.getFromUserId();
        String userName = msg.getFromUserName();
        System.out.println(String.format("%s %s:%s -> %s", LocalDateTime.now(), userId, userName, msg.getMessage()));
    }

}
