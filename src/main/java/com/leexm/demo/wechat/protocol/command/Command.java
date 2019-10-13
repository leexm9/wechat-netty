package com.leexm.demo.wechat.protocol.command;

/**
 * @author leexm
 * @date 2019-10-13 16:40
 */
public interface Command {

    /** 登录请求 */
    Byte LOGIN_REQUEST = 1;

    /** 登录响应 */
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

}
