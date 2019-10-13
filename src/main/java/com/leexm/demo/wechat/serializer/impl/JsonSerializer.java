package com.leexm.demo.wechat.serializer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leexm.demo.wechat.serializer.Serializer;
import com.leexm.demo.wechat.serializer.SerializerAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 使用 JSON 进行序列化和反序列化
 *
 * @author leexm
 * @date 2019-10-13 16:48
 */
public class JsonSerializer implements Serializer {

    private static final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
            return mapper.readValue(bytes, clazz);
        } catch (IOException e) {
            logger.error("[JsonSerializer] convert byte array:{} to object error", bytes, e);
        }
        return null;
    }

}
