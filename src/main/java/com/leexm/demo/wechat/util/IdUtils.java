package com.leexm.demo.wechat.util;

import java.util.UUID;

/**
 * @author leexm
 * @date 2019-10-16 00:36
 */
public class IdUtils {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

}
