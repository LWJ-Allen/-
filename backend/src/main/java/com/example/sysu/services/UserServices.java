package com.example.sysu.services;

import com.example.sysu.bean.ApiResult;
import com.example.sysu.repository.UserManager;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Objects;

public class UserServices {
    private static UserManager userManager = UserManager.getInstance();

    public static ObjectNode register(String userId, String userName,
                                      String userAccount, String userPassword,
                                      String userPhone) {
        if(userManager.addUser(userId, userName, userAccount, userPassword, userPhone)) {
            return new ApiResult<String>("101", "success", "").toJson();
        }
        else return new ApiResult<String>("102", "该学号已存在！", "").toJson();
    }

    public static ObjectNode login(String account
                                   String password) {
        int state = userManager.login(account,password)
        if(state == 1) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode res = objectMapper.createObjectNode();
            
            res.put("user_id",userManager.getUserId());
            res.put("user_name",userManager.getUserName());
            res.put("user_account",userManager.getUserAccount());
            res.put("user_password",userManager.getUserPassword());
            res.put("user_phone",userManager.getUserPhone());
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
