package com.leexm.demo.wechat.serializer;

import com.leexm.demo.wechat.serializer.impl.JsonSerializer;

/**
 * @author leexm
 * @date 2019-10-13 16:48
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
