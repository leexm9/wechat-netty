package com.leexm.demo.wechat.protocol.response;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.command.Command;

/**
 * @author leexm
 * @date 2019-10-15 23:42
 */
public class LogoutResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }

}
