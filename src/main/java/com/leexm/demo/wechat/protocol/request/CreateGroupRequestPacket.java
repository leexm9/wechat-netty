package com.leexm.demo.wechat.protocol.request;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.command.Command;
import com.leexm.demo.wechat.util.JsonUtils;

import java.util.List;

/**
 * @author leexm
 * @date 2019-10-15 01:32
 */
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIds;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return JsonUtils.obj2String(this);
    }
}
