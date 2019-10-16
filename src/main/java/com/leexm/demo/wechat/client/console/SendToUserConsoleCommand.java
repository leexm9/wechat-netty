package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 23:08
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发消息给某个用户:");
        String text = scanner.nextLine();
        int index = StringUtils.indexOf(text, " ");
        MessageRequestPacket packet = new MessageRequestPacket();
        packet.setToUserId(StringUtils.substring(text, 0, index));
        packet.setMessage(StringUtils.substring(text, index + 1));
        channel.writeAndFlush(packet);
    }

}
