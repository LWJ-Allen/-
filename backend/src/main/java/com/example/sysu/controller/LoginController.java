package com.example.sysu.controller;

import com.example.sysu.bean.ApiResult;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ObjectNode login(@RequestParam boolean isStore, @RequestParam String account, @RequestParam String password) {
        System.out.println(isStore + " " + account + " " + password);
        return new ApiResult<String>("111", "222", account).toJson();
    }

}
