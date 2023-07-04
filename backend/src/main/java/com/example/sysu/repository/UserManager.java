package com.example.sysu.repository;

import com.example.sysu.bean.User;

import java.util.HashMap;

public class UserManager {
    private static UserManager userManager = new UserManager();
    private HashMap<String, User> users;

    private UserManager() { this.users = new HashMap<>(); }

    public static UserManager getInstance() { return userManager; }

    public boolean addUser(String userId, String userName,
                    String userAccount, String userPassword,
                    String userPhone) {
        if(users.containsKey(userId)) {
            return false;
        }
        users.put(userId, new User(userId, userName,
                userAccount, userPassword,
                userPhone));
        System.out.printf("Add User id: %s  name: %s.\n%n", userId, userName);
        return true;
    }
}
