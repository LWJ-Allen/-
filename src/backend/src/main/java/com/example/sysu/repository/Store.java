package com.example.sysu.repository;

import com.example.sysu.bean.Food;
import com.example.sysu.utils.IdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
@Getter

public class Store {
    private final String storeId;
    private String storeName;
    private String storeAccount;
    private String storePassword;
    private String storeDescription;

    private String directorName;
    private String directorId;
    private String directorPhone;

    private HashMap<String, Food> menu;
    public Store(String storeName, String storeAccount, String storePassword,
                 String storeDescription,
                 String directorName, String directorId, String directorPhone) {
        this.storeName = storeName;
        this.storeAccount = storeAccount;
        this.storePassword = storePassword;
        this.storeDescription = storeDescription;
        this.directorName = directorName;
        this.directorId = directorId;
        this.directorPhone = directorPhone;
        this.storeId = IdGenerator.storeIdGenerate();
        this.menu = new HashMap<>();
        System.out.println("create a store: " + this.storeId + " " + this.storeName);
    }

    public boolean verify(String password) {
        return this.storePassword.equals(password);
    }

    public Food findFoodByName(String foodName) {
        if(this.menu.containsKey(foodName)) return this.menu.get(foodName);
        return null;
    }

    public boolean addFood(String foodName, double foodPrice, String foodDescription) {
        if(this.menu.containsKey(foodName)) {
            return false;
        }
        String foodId = IdGenerator.foodIdGenerate(Integer.parseInt(this.storeId));
        this.menu.put(foodName, new Food(foodId, foodName, foodPrice, foodDescription));
        return true;
    }

    public boolean eraseFood(String foodName) {
        if(this.menu.containsKey(foodName)) {
            this.menu.remove(foodName);
            return true;
        }
        return false;
    }
}
