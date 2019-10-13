package com.leexm.demo.wechat.codec;

import com.leexm.demo.wechat.protocol.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author leexm
 * @date 2019-10-14 00:36
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCode.getInstance().decode(in));
    }

}
