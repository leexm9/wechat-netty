package com.leexm.demo.wechat.protocol.response;

import com.leexm.demo.wechat.protocol.Packet;
import com.leexm.demo.wechat.protocol.command.Command;
import com.leexm.demo.wechat.util.JsonUtils;

/**
 * @author leexm
 * @date 2019-10-16 01:20
 */
public class JoinGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private String reason;

    /** 标记加入群聊响应分类
     * 0:表示加入群组请求，1:通知群组成员新用户加入
     */
    private int type;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JsonUtils.obj2String(this);
    }
}
