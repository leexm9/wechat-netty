package com.leexm.demo.wechat.protocol.request;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.command.Command;

/**
 * @author leexm
 * @date 2019-10-15 23:15
 */
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }

}
