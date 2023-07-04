package com.example.sysu.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sysu.services.StoreServices;

@RestController
public class FoodController {
    @RequestMapping(path = "/query/store", method = RequestMethod.POST)
    public ObjectNode addFood(@RequestParam String account,@RequestParam String foodName, @RequestParam double foodPrice,@RequestParam String foodDescription) {
        return StoreServices.addFood(account,foodName,foodPrice,foodDescription);
    }
}
