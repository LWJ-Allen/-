package com.example.sysu.controller;

import com.example.sysu.services.StoreServices;
import com.example.sysu.services.UserServices;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @RequestMapping(path = "/register/user", method = RequestMethod.POST)
    public ObjectNode userRegister(@RequestParam String id, @RequestParam String name, @RequestParam String account,
                                   @RequestParam String password, @RequestParam String phone) {
        return UserServices.register(id, name, account, password, phone);
    }

    @RequestMapping(path = "/register/store", method = RequestMethod.POST)
    public ObjectNode storeRegister(@RequestParam String name,
                                    @RequestParam String account, @RequestParam String password,
                                    @RequestParam String description,
                                    @RequestParam String directorName, @RequestParam String directorId,
                                    @RequestParam String directorPhone
                                    ) {
        return StoreServices.register(name, account, password,
                description,
                directorName, directorId, directorPhone);

    }

}
