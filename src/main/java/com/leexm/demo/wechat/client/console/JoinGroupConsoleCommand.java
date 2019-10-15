package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 01:43
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入 groupId，加入群聊:");
        String groupId = scanner.nextLine();
        JoinGroupRequestPacket packet = new JoinGroupRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }

}
