package com.example.sysu.repository;

import com.example.sysu.bean.Order;
import com.example.sysu.utils.IdGenerator;
import com.example.sysu.repository.StoreManager;
import com.example.sysu.repository.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;

public class OrderManager {
    private static OrderManager orderManager = new OrderManager();
    private static StoreManager storeManager = StoreManager.getInstance();
    private static UserManager userManager = UserManager.getInstance();
    private static HashMap<String, Order> orders;

    private OrderManager() { this.orders = new HashMap<>(); }

    public static OrderManager getInstance() { return orderManager; }

    public static boolean addOrder(String userId,
                            String storeId,
                            String foodId,
                            String orderDate,
                            double orderPrice){
        String orderid = IdGenerator.OrderIdGenerate();
        orders.put(orderid, new Order(userId,storeId,foodId,orderid,orderDate,orderPrice));

        return true;
    }


    public static ArrayNode getAllStoreOrder(String id){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode Ares = objectMapper.createArrayNode();
        for (HashMap.Entry < String, Order > entry: orders.entrySet()) {
            Order tmp = entry.getValue();

            String foodname = StoreManager.getFoodNameByID(tmp.getStoreId(),tmp.getFoodId());
            String username = userManager.getUserNameByID(tmp.getUserId());
            if(tmp.getStoreId().equals(id)){
                ObjectNode res = objectMapper.createObjectNode();
                
                res.put("userid",tmp.getUserId()); 
                res.put("username",username);
                res.put("storeid",tmp.getStoreId()); 
                res.put("foodid",tmp.getFoodId());
                res.put("foodname",foodname);
                res.put("orderid",tmp.getOrderId());
                res.put("orderdate",tmp.getOrderDate()); 
                res.put("orderprice",tmp.getOrderPrice());
                Ares.add(res);
            }
        } 
        return Ares;
    }

    public static ArrayNode getAllUserOrder(String id){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode Ares = objectMapper.createArrayNode();
        for (HashMap.Entry < String, Order > entry: orders.entrySet()) {
            Order tmp = entry.getValue();

            String foodname = StoreManager.getFoodNameByID(tmp.getStoreId(),tmp.getFoodId());
            String storename = StoreManager.getStoreNameByID(tmp.getStoreId());
            if(tmp.getUserId().equals(id)){
                ObjectNode res = objectMapper.createObjectNode();
                res.put("userid",tmp.getUserId()); 
                res.put("storeid",tmp.getStoreId()); 
                res.put("storename",storename); 
                res.put("foodid",tmp.getFoodId());
                res.put("foodname",foodname);
                res.put("orderid",tmp.getOrderId());
                res.put("orderdate",tmp.getOrderDate()); 
                res.put("orderprice",tmp.getOrderPrice());
                Ares.add(res);
            }
        } 
        return Ares;
    }
}

