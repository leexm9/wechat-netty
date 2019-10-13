package com.leexm.demo.wechat.protocol;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author leexm
 * @date 2019-10-13 16:37
 */
public abstract class Packet {

    /** 协议版本 */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    @JsonIgnore
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
