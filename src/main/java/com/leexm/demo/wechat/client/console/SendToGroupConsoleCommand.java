package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 02:06
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个群组:");
        GroupMessageRequestPacket packet = new GroupMessageRequestPacket();
        String text = scanner.nextLine();
        int index = StringUtils.indexOf(text," ");
        packet.setGroupId(StringUtils.substring(text, 0, index));
        packet.setMessage(StringUtils.substring(text, index + 1));
        channel.writeAndFlush(packet);
    }

}
