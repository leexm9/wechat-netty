package com.leexm.demo.wechat.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author leexm
 * @date 2019-10-15 01:18
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
