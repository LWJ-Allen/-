package com.example.sysu.inc;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private int idCounter;
    private List<User> userList;
    public UserManager(){
        System.out.println("create UserManager");
        idCounter = 0;
        userList = new ArrayList<User>();

        //使用这个用户名和密码进行测试
        register("user1", "password1");
    }

    public User findUserName(String userName){
        for(User user : userList){
            if(user.userName.equals(userName)){
                return user;
            }
        }
        return null;
    }

    public User findUserId(int userId){
        for(User user : userList){
            if(user.userId == userId){
                return user;
            }
        }
        return null;
    }

    public boolean login(String userName, String password){
        User tmpUser = findUserName(userName);
        if(tmpUser == null){
            //无此用户
            return false;
        }
        return tmpUser.verify(password);
    }

    public boolean register(String userName, String password){
        idCounter += 1;
        User tmpUser = findUserName(userName);
        if(tmpUser != null){
            //用户名已存在
            return false;
        }
        User newUser = new User(idCounter, userName, password);
        userList.add(newUser);
        return true;
    }

}
