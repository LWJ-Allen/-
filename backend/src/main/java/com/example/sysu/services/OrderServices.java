package com.example.sysu.services;

import com.example.sysu.bean.ApiResult;
import com.example.sysu.repository.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Objects;


public class OrderServices {
    private static OrderManager orderManager = OrderManager.getInstance();
    
    public static ObjectNode addOrder(String userId,
                                      String storeId,
                                      String foodId,
                                      Calendar orderDate,
                                      double orderPrice){
        if(OrderManager.addOrder(userId,Store,foodId,orderDate,orderPrice)){
            return new ApiResult<String>("101", "success", "").toJson();
        }
        else {
            return new ApiResult<String>("102", "order add fail", "").toJson();
        }
    }

    public static ObjectNode storeQueryOrder(String id){
        ArrayNode storeOrderList  = OrderManager.getAllStoreOrder(id);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode res = objectMapper.createObjectNode();
        res.putArray("store_order_list").addAll(storeOrderList);
        return new ApiResult<ObjectNode>("101", "success", res).toJson();
    }

    public static ObjectNode userQueryOrder(String id){
        ArrayNode userOrderList  = OrderManager.getAllUserOrder(id);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode res = objectMapper.createObjectNode();
        res.putArray("user_order_list").addAll(userOrderList);
        return new ApiResult<ObjectNode>("101", "success", res).toJson();
    }
}
