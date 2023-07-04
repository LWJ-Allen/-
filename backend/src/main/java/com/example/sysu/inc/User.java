package com.example.sysu.inc;
public class User {
    public int userId;
    public String userName;
    private String password;
    public User(int userId, String userName, String password){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        System.out.println("create an user:" + userName);
    }

    public boolean verify(String password){
        //java 判断string是否相等要用equal
        return password.equals(this.password);
    }
}
