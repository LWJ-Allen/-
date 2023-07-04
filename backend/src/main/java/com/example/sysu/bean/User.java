package com.example.sysu.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class User {
    private final String userId;
    private final String userName;
    private String usrAccount;
    private String userPassword;
    private String userPhone;

    public boolean verify(String psd) {
        return this.userPassword.equals(psd);
    }
}
