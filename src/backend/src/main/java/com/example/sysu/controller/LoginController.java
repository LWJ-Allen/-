package com.example.sysu.controller;

import com.example.sysu.bean.ApiResult;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sysu.services.StoreServices;
import com.example.sysu.services.UserServices;

@RestController
public class LoginController {
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ObjectNode login(@RequestParam boolean isStore, @RequestParam String account, @RequestParam String password) {
        System.out.println(isStore + " " + account + " " + password);
        if(isStore){
            return StoreServices.login(account, password);
        }
        else{
            return UserServices.login(account, password);
        }
    }

}
