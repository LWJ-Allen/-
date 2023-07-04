package com.example.sysu.controller;

import com.example.sysu.bean.ApiResult;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sysu.services.StoreServices;
import com.example.sysu.services.UserServices;
import com.example.sysu.services.OrderServices;

@RestController
public class OrderController {
    @RequestMapping(path = "/order/add", method = RequestMethod.POST)
    public ObjectNode addorder(@RequestParam String userId, 
                               @RequestParam String storeId,
                               @RequestParam String foodId,
                               @RequestParam Calendar orderDate,
                               @RequestParam double orderPrice) {
        return OrderServices.addOrder(userId,storeId,foodId,orderid,orderDate,orderPrice);
    }

    @RequestMapping(path = "/order/storequery", method = RequestMethod.POST)
    public ObjectNode storeQueryOrder(@RequestParam String storeId) {
        return OrderServices.storeQueryOrder(storeId);
    }

    @RequestMapping(path = "/order/userquery", method = RequestMethod.POST)
    public ObjectNode userQueryOrder(@RequestParam String userId) {
        return OrderServices.userQueryOrder(userId);
    }
}
