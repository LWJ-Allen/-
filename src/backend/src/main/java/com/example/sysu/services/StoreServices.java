package com.example.sysu.services;

import com.example.sysu.bean.ApiResult;
import com.example.sysu.repository.Store;
import com.example.sysu.repository.StoreManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class StoreServices {
    private static StoreManager storeManager = StoreManager.getInstance();
    public static ObjectNode register(String name,
                                      String account, String password,
                                      String description,
                                      String directorName, String directorId,
                                      String directorPhone) {
        if(storeManager.addStore(name, account, password, description,
                directorName, directorId, directorPhone)) {
            return new ApiResult<String>("101", "success", "").toJson();
        }
        return new ApiResult<String>("102", "店名已存在！", "").toJson();
    }

    public static ObjectNode login(String account,
                                   String password) {
        int state = storeManager.login(account,password);
        if(state == 1) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode res = objectMapper.createObjectNode();
            
            res.put("store_name",storeManager.getStoreName(account));
            res.put("store_account",storeManager.getStoreAccount(account));
            res.put("store_password",storeManager.getStorePassword(account));
            res.put("store_des",storeManager.getStoreDescription(account));
            res.put("store_d_name",storeManager.getDirectorName(account));
            res.put("store_d_id",storeManager.getDirectorId(account));
            res.put("store_d_phone",storeManager.getDirectorPhone(account));
            res.put("store_id",storeManager.getStoreId(account));
            return new ApiResult<ObjectNode>("101", "success", res).toJson();
        }
        else if(state == 0){
            return new ApiResult<String>("102", "account fail", "").toJson();
        }
        else if(state == 2){
            return new ApiResult<String>("102", "password fail", "").toJson();
        }
        return new ApiResult<String>("103", "Error", "").toJson();
    }
    
    public static ObjectNode queryAllstore(){
        ArrayNode storeList  = storeManager.getAllStore();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode res = objectMapper.createObjectNode();
        res.putArray("store_list").addAll(storeList);
        return new ApiResult<ObjectNode>("101", "success", res).toJson();
    }

    public static ObjectNode queryAllmenu(String account){
        ArrayNode menuList  = storeManager.getAllmenu(account);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode res = objectMapper.createObjectNode();
        res.putArray("menu_list").addAll(menuList);
        return new ApiResult<ObjectNode>("101", "success", res).toJson();
    }

    public static ObjectNode addFood(String account,String foodName, double foodPrice, String foodDescription){
        if(storeManager.addFood(account,foodName,foodPrice,foodDescription)){
            return new ApiResult<String>("101", "food add success", "").toJson();
        }
        else{
            return new ApiResult<String>("101", "food add fail", "").toJson();
        }
    }
}
