package com.obf.movie.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.payex.vas.common.vasutil.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtil {
    private static final Logger _log = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper;


    @Autowired
    public JsonUtil(ObjectMapper objectMapper) {
        JsonUtil.mapper = objectMapper;
    }

    public static String mapToString(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T mapToObject(String source, Class<T> clazz) throws IOException {
        return mapper.readValue(source, clazz);
    }

    public static <T> T mapToObject(byte[] source, Class<T> clazz) throws IOException {
        return mapper.readValue(source, clazz);
    }

    public static String prettyPrintJsonObj(Object jsonObj) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
    }

    public static String getValueFromJsonString(String fieldName, String jsonString) throws IOException {
        JsonNode jNode = mapper.readTree(jsonString);
        JsonNode result = jNode.findValue(fieldName);
        return (result != null && !result.isNull()) ? (result.isTextual() ? result.textValue() : result.toString()) : null;
    }

    public static String createJsonObject(String key, String value) {
        ObjectNode obj = mapper.createObjectNode();
        obj.put(key, value);
        return obj.toString();
    }

    public static String appendToJson(String jsonString, String key, String value) {
        try {
            if (StringUtil.isNullOrEmpty(jsonString))
                return createJsonObject(key, value);

            ObjectNode obj = (ObjectNode) mapper.readTree(jsonString);

            if (isJSONValid(value)) {
                obj.set(key, mapper.readTree(value));
            } else {
                obj.put(key, value);
            }
            return obj.toString();
        } catch (IOException ex) {
            _log.error("Failed to add json-element with key {} to json-object {}.. returning original json-object", key, jsonString, ex);
            return jsonString;
        }
    }

    private static boolean isJSONValid(String jsonInString) {
        try {
            if (jsonInString != null
                && ((jsonInString.startsWith("{") && jsonInString.endsWith("}")) || (jsonInString.startsWith("[") && jsonInString.endsWith("]")))) {
                mapper.readTree(jsonInString);
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }
}
