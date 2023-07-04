package com.example.sysu.services;

import com.example.sysu.bean.ApiResult;
import com.example.sysu.bean.Store;
import com.example.sysu.repository.StoreManager;
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

    public static ObjectNode login(String account
                                   String password) {
        int state = storeManager.login(account,password)
        if(state == 1) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode res = objectMapper.createObjectNode();
            
            res.put("store_name",storeManager.getStoreName());
            res.put("store_account",storeManager.getStoreAccount());
            res.put("store_password",storeManager.getStorePassword());
            res.put("store_des",storeManager.getStoreDescription());
            res.put("store_d_name",storeManager.getDirectorName());
            res.put("store_d_id",storeManager.getDirectorId());
            res.put("store_d_phone",storeManager.getDirectorPhone());
            res.put("store_id",storeManager.getStoreId());
            return new ApiResult<String>("101", "success",res.toJson()).toJson();
        }
        else if(state == 0){
            return new ApiResult<String>("102", "account fail", "").toJson();
        }
        else if(state == 2){
            return new ApiResult<String>("102", "password fail", "").toJson();
        }
    }
}
