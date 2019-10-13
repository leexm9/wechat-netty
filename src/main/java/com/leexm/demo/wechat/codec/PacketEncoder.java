package com.leexm.demo.wechat.codec;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author leexm
 * @date 2019-10-14 00:48
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCode.getInstance().encode(out, msg);
    }

}
