package com.example.sysu.services;

import com.example.sysu.bean.ApiResult;
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
}
