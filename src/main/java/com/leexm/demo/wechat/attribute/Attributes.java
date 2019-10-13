package com.leexm.demo.wechat.attribute;

import io.netty.util.AttributeKey;

/**
 * @author leexm
 * @date 2019-10-13 22:39
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
