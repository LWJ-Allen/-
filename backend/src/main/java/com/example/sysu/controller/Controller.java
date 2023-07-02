package com.example.sysu.controller;


import com.example.sysu.bean.ApiResult;
import com.example.sysu.inc.Inter;
import com.example.sysu.inc.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// identity 表示身份，1为客户

@RestController
public class LoginController {
    @RequestMapping(path="/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ObjectNode login(String username, String password,int identity){
        Inter inter = getInstance(); 
        if(identity == 1){
            if(inter.userLogin(username,password)) {
                List<Store> storeList =inter.getStores();
                String store_msg; 
                for(Store store : storeList){
                    store_msg = store_msg + "\n" + str(store.storeId) + " " + store.storeName;
                    userOrders.add(order);
                }
                return new ApiResult<String>("200", "登录成功", store_msg).toJson();
            }
            else return new ApiResult<String>("201", "登录失败", "").toJson();
        }
        else {
            if(inter.storeLogin(username,password)) return new ApiResult<String>("200", "登录成功", "").toJson();
            else return new ApiResult<String>("201", "登录失败", "").toJson();
        }
    }
}

@RestController
public class RegisterController {
    @RequestMapping(path="/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ObjectNode register(String username, String password,int identity){
        Inter inter = getInstance(); 
        if(identity == 1){
            if(inter.userRegister(username,password)) return new ApiResult<String>("200", "注册成功", "").toJson();
            else return new ApiResult<String>("201", "登录失败", "").toJson();
        }
        else {
            if(inter.storeRegister(username,password)) return new ApiResult<String>("200", "注册成功", "").toJson();
            else return new ApiResult<String>("201", "登录失败", "").toJson();
        }
    }
}

@RestController
public class OrderController {
    @RequestMapping(path="/order", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") // todo： 路由需要根据店铺更变，店铺/order 
    public ObjectNode Order(int userId, int storeId, String foodName){
        Inter inter = getInstance(); 
        if(inter.createOrder(userId,storeId,foodName)) return new ApiResult<String>("200", "下单成功", "").toJson();
        else return new ApiResult<String>("201", "下单失败", "").toJson()
    }
}

@RestController
public class OrderController {
    @RequestMapping(path="/manu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") // todo： 路由需要根据店铺更变，店铺/menu 
    public ObjectNode menu(int storeId){
        Inter inter = getInstance(); 
        List<Food> Menu = inter.getMenu(storeId)
        String food_msg
        for(Food food : Menu){
            food_msg = food_msg + "\n" + food.foodName 
                                + "\n" + str(food.price)   
        }
        return new ApiResult<String>("200", "菜单查询成功", food_msg).toJson();
    }
}

@RestController
public class checkOrderController {
    @RequestMapping(path="/order_check", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") 
    public ObjectNode Order(int Id,int identity){
        Inter inter = getInstance(); 
        if(identity == 1){
            List<Order> orderList = inter.getUserOrders(id);
            String order_msg;
            for(Store order : orderList){
                order_msg = order_msg + "\n" + str(order.userId)
                                      + "\n" + str(order.storeId)
                                      + "\n" + order.foodName
                                      + "\n" + str(order.orderId)
                                      + "\n" + str(order.flag)
                                      + "\n" + str(order.date)
                                      + "\n" + str(order.price);
            }
            return new ApiResult<String>("200", "订单查询成功", order_msg).toJson();
        }
        else {
            List<Order> orderList = inter.getStoreOrders(id);
            String order_msg;
            for(Store order : orderList){
                order_msg = order_msg + "\n" + str(order.userId)
                                      + "\n" + str(order.storeId)
                                      + "\n" + order.foodName
                                      + "\n" + str(order.orderId)
                                      + "\n" + str(order.flag)
                                      + "\n" + str(order.date)
                                      + "\n" + str(order.price);
            }
            return new ApiResult<String>("200", "订单查询成功", order_msg).toJson();
        }
        return new ApiResult<String>("201", "订单查询失败","").toJson();
    }
}
