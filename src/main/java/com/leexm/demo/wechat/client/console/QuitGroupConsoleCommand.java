package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 01:52
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入 groupId，退出群聊：");
        QuitGroupRequestPacket packet = new QuitGroupRequestPacket();
        String groudId = scanner.nextLine();
        packet.setGroupId(groudId);
        channel.writeAndFlush(packet);
    }

}
