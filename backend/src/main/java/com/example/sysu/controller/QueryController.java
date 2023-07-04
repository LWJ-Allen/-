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
public class QueryController {
    @RequestMapping(path = "/query/store", method = RequestMethod.GET)
    public ObjectNode query() {
        return StoreServices.queryAll()
    }

}
