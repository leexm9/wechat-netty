package com.leexm.demo.wechat.protocol.response;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.command.Command;
import com.leexm.demo.wechat.session.Session;
import com.leexm.demo.wechat.util.JsonUtils;

import java.util.List;

/**
 * @author leexm
 * @date 2019-10-16 01:34
 */
public class ListGroupMembersResponsePacket extends Packet {

    private boolean success;

    private String reason;

    private String groupId;

    private List<Session> sessions;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return JsonUtils.obj2String(this);
    }
}
