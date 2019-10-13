package com.leexm.demo.wechat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author leexm
 * @date 2019-10-13 17:45
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String obj2String(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return mapper.readValue(str, clazz);
        } catch (IOException e) {
            logger.error("[JsonUtils] convert string:{} to object:{} exception,", str, clazz, e);
        }
        return null;
    }

}
