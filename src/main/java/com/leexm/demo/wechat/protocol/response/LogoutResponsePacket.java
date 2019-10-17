package com.leexm.demo.wechat.protocol.response;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.command.Command;
import com.leexm.demo.wechat.util.JsonUtils;

/**
 * @author leexm
 * @date 2019-10-15 23:42
 */
public class LogoutResponsePacket extends Packet {

    private boolean success;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return JsonUtils.obj2String(this);
    }
}
