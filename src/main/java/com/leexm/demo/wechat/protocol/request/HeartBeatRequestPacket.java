package com.leexm.demo.wechat.protocol.request;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.command.Command;

/**
 * @author leexm
 * @date 2019-10-17 01:26
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
