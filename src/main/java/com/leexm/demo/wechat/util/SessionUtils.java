package com.leexm.demo.wechat.util;

import com.leexm.demo.wechat.attribute.Attributes;
import com.leexm.demo.wechat.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leexm
 * @date 2019-10-13 22:42
 */
public class SessionUtils {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> groupChannelMap = new ConcurrentHashMap<>();

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> login = channel.attr(Attributes.LOGIN);
        return login.get() != null;
    }

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + "下线");
        }
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }

    public static Session getSession(Channel channel) {
        Attribute<Session> session = channel.attr(Attributes.SESSION);
        return session.get();
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupChannelMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupChannelMap.get(groupId);
    }

}
