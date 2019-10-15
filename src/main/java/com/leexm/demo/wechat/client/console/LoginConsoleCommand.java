package com.leexm.demo.wechat.client.console;

import com.leexm.demo.wechat.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author leexm
 * @date 2019-10-15 23:28
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户名登录: ");
        // 创建登录对象
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        String userName = scanner.nextLine();
        loginPacket.setUsername(userName);
        loginPacket.setPassword("12245");
        channel.writeAndFlush(loginPacket);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
