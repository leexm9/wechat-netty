package com.leexm.demo.wechat.server.handler;

import com.leexm.demo.wechat.util.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author leexm
 * @date 2019-10-14 08:18
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtils.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 已经验证通过的连接，无需重复验证，提升效率和节省资源
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtils.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再此验证，AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接");
        }
    }
}
