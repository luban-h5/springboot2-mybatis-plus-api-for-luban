package com.luban.api.lubanapi.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JSON 转换工具
 *
 * @author WeiHongBin
 */
@Slf4j
@Component
public class JSON {

    @Autowired
    private ObjectMapper objectMapper;

    public String toJSON(Object jsonObj) {
        try {
            return objectMapper.writeValueAsString(jsonObj);
        } catch (JsonProcessingException e) {
            log.error("JSON 转换异常 转换异常的数据类型: " + jsonObj.getClass().getName(), e);
        }
        return jsonObj.toString();
    }


    public Map toMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            log.error("JSON 转换异常", e);
        }
        return null;
    }

    public List<Map<String, String>> toListMapStr(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Map<String, String>>>() {
            });
        } catch (IOException e) {
            log.error("JSON 转换异常", e);
        }
        return null;
    }


    public List<String> toListString(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            log.error("JSON 转换异常", e);
        }
        return null;
    }
}
