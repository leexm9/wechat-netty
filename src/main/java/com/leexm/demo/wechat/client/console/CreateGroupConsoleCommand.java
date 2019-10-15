package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 01:23
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String SEPARATOR = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        System.out.print("[建立群聊]输入 userId 列表，userId 之前使用英文逗号分隔:");
        String ids = scanner.nextLine();
        packet.setUserIds(Arrays.asList(StringUtils.split(ids, SEPARATOR)));
        channel.writeAndFlush(packet);
    }
}
