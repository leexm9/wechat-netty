package com.leexm.demo.wechat.protocol.command;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.PacketCode;
import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;

import java.util.UUID;

/**
 * @author leexm
 * @date 2019-10-13 17:34
 */
public class PacketCodeTest {

    @Test
    public void test() {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUsername("Jack");
        packet.setPassword("1278736");
        System.out.println("==================");
        System.out.println(packet);

        ByteBuf byteBuf = PacketCode.getInstance().encode(ByteBufAllocator.DEFAULT, packet);

        Packet packet1 = PacketCode.getInstance().decode(byteBuf);
        System.out.println("==================");
        System.out.println(packet1);
        System.out.println("command：" + packet1.getCommand());
    }

}
