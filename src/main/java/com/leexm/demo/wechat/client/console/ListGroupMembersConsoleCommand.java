package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 01:58
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，获取群成员列表：");
        ListGroupMembersRequestPacket packet = new ListGroupMembersRequestPacket();
        String groupId = scanner.nextLine();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }

}
