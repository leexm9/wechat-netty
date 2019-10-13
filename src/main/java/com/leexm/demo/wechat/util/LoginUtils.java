package com.leexm.demo.wechat.util;

import com.leexm.demo.wechat.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author leexm
 * @date 2019-10-13 22:42
 */
public class LoginUtils {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> login = channel.attr(Attributes.LOGIN);
        return login.get() != null;
    }

}
