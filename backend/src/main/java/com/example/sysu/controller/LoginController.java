package com.example.sysu.controller;


import com.example.sysu.bean.ApiResult;
import com.example.sysu.inc.Inter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @RequestMapping(path="/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ObjectNode login(){
        return new ApiResult<String>("111", "111", "111").toJson();
    }

}
