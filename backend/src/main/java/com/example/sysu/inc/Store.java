package com.example.sysu.inc;
import java.util.ArrayList;
import java.util.List;

public class Store {
    public int storeId;
    public String storeName;
    public List <Food> menu;
    private String password;

    public Store(int storeId, String storeName, String password){
        this.storeId = storeId;
        this.storeName = storeName;
        this.password = password;
        menu = new ArrayList<Food>();
        System.out.println("create a store:" + storeName);
    }

    public boolean verify(String password){
        return password.equals(this.password);
    }

    public Food findFood(String foodName){
        for(Food food : menu){
            if(food.foodName.equals(foodName)){
                return food;
            }
        }
        return null;
    }

    public boolean addFood(String foodName, double price){
        Food food = findFood(foodName);
        if(food != null){
            //菜名已存在
            return false;
        }
        menu.add(new Food(foodName, price));
        return true;
    }

    public boolean eraseFood(String foodName){
        Food food = findFood(foodName); 
        if(food != null){
            menu.remove(food);
        }   
        else {
            return false; 
        }
        return false;
    }
}
