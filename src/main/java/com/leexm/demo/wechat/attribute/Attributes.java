package com.leexm.demo.wechat.attribute;

import com.leexm.demo.wechat.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author leexm
 * @date 2019-10-13 22:39
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
