package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 23:14
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket packet = new LogoutRequestPacket();
        channel.writeAndFlush(packet);
    }

}
