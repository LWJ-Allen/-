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

    public int login(String account, String password){
        Store tmpUser = users.get(account);
        if(tmpUser == null){
            return 0;
        }
        if(tmpUser.verify(password)){
            return 1; 
        }
        else {
            return 2; 
        }
    }

    public String getUserId(String account){
        User tmp = users.get(account);
        return tmp.getUserId();
    }

    public String getUserName(String account){
        User tmp = users.get(account);
        return tmp.getUserName();
    }

    public String getUserAccount(String account){
        User tmp = users.get(account);
        return tmp.getUserAccount();
    }

    public String getUserPassword(String account){
        User tmp = users.get(account);
        return tmp.getUserPassword();
    }

    public String getUserPhone(String account){
        User tmp = users.get(account);
        return tmp.getUserPhone();
    }
}
