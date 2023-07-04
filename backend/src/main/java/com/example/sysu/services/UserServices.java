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
}
