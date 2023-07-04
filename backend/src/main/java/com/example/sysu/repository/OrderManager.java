package com.example.sysu.repository;

import com.example.sysu.bean.Order;
import com.example.sysu.utils.IdGenerator;

import java.util.HashMap;

public class OrderManager {
    private static OrderManager orderManager = new OrderManager();
    private HashMap<String, Order> orders;

    private OrderManager() { this.orders = new HashMap<>(); }

    public static OrderManager getInstance() { return orderManager; }

    public boolean addOrder(String userId,
                            String storeId,
                            String foodId,
                            Calendar orderDate,
                            double orderPrice){
        if(orders.containsKey(orderid)) return false;
        String orderid = IdGenerator.OrderIdGenerate();
        orders.put(orderid, new Order(userId,storeId,foodId,orderid,orderDate,orderPrice));

        return true;
    }


    public ArrayNode getAllStoreOrder(String id){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode Ares = objectMapper.createArrayNode();
        for (HashMap.Entry < String, Order > entry: menuList.entrySet()) {  
            Order tmp = entry.getValue();

            if(tmp.getStoreId().equals(id)){
                ObjectNode res = objectMapper.createObjectNode();

                res.put("userid",tmp.getUserId()); 
                res.put("storeid",tmp.getStoreId()); 
                res.put("foodid",tmp.getFoodId());
                res.put("orderid",tmp.getOrderId());
                res.put("orderdate",tmp.getOrderDate()); 
                res.put("orderprice",tmp.getOderPrice());
                Ares.add(res);
            }
        } 
        return Ares;
    }

    public ArrayNode getAllUserOrder(String id){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode Ares = objectMapper.createArrayNode();
        for (HashMap.Entry < String, Order > entry: menuList.entrySet()) {  
            Order tmp = entry.getValue();

            if(tmp.getUserId().equals(id)){
                ObjectNode res = objectMapper.createObjectNode();

                res.put("userid",tmp.getUserId()); 
                res.put("storeid",tmp.getStoreId()); 
                res.put("foodid",tmp.getFoodId());
                res.put("orderid",tmp.getOrderId());
                res.put("orderdate",tmp.getOrderDate()); 
                res.put("orderprice",tmp.getOderPrice());
                Ares.add(res);
            }
        } 
        return Ares;
    }
}

