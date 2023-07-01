package com.example.sysu.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class ApiResult<T> {
    private String code;
    private String msg;
    private T data;

    public ObjectNode toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode res = objectMapper.createObjectNode();

        res.put("code", code);
        res.put("msg", msg);
        res.set("data", objectMapper.convertValue(data, JsonNode.class));
        return res;
    }
}
