package com.example.sysu.repository;

import com.example.sysu.bean.Food;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;

public class StoreManager {
    private static StoreManager storeManager = new StoreManager();
    private HashMap<String, Store> stores;

    private StoreManager() {this.stores = new HashMap<>();}

    public static StoreManager getInstance() {return storeManager;}

    public boolean addStore(String name,
                            String account, String password,
                            String description,
                            String directorName, String directorId,
                            String directorPhone) {
        if(stores.containsKey(account)) return false;

        stores.put(account, new Store(name, account, password,
                description, directorName, directorId, directorPhone));

        return true;

    }

    public boolean addFood(String account,String foodName, double foodPrice, String foodDescription){
        Store tmpStore = stores.get(account);

        return tmpStore.addFood(foodName,foodPrice,foodDescription);
    }

    public int login(String account, String password){
        Store tmpStore = stores.get(account);
        if(tmpStore == null){
            return 0;
        }
        if(tmpStore.verify(password)){
            return 1; 
        }
        else {
            return 2; 
        }
    }

    public String getStoreName(String account){
        Store tmp = stores.get(account);
        return tmp.getStoreName();
    }

    public String getStoreAccount(String account){
        Store tmp = stores.get(account);
        return tmp.getStoreAccount();
    }

    public String getStorePassword(String account){
        Store tmp = stores.get(account);
        return tmp.getStorePassword();
    }
    
    public String getStoreDescription(String account){
        Store tmp = stores.get(account);
        return tmp.getStoreDescription();
    }

    public String getDirectorName(String account){
        Store tmp = stores.get(account);
        return tmp.getDirectorName();
    }

    public String getDirectorId(String account){
        Store tmp = stores.get(account);
        return tmp.getDirectorId();
    }

    public String getDirectorPhone(String account){
        Store tmp = stores.get(account);
        return tmp.getDirectorPhone();
    }

    public String getStoreId(String account){
        Store tmp = stores.get(account);
        return tmp.getStoreId();
    }

    public ArrayNode getAllStore(){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode Ares = objectMapper.createArrayNode();
        for (HashMap.Entry < String, Store > entry: stores.entrySet()) {  
            Store tmp = entry.getValue();
            ObjectNode res = objectMapper.createObjectNode();
            
            res.put("store_name",tmp.getStoreName());
            res.put("store_account",tmp.getStoreAccount());
            res.put("store_password",tmp.getStorePassword());
            res.put("store_des",tmp.getStoreDescription());
            res.put("store_d_name",tmp.getDirectorName());
            res.put("store_d_id",tmp.getDirectorId());
            res.put("store_d_phone",tmp.getDirectorPhone());
            res.put("store_id",tmp.getStoreId());
            Ares.add(res);
        } 
        return Ares;
    }

    public ArrayNode getAllmenu(String account){
        Store tmp = stores.get(account);
        HashMap<String, Food> menuList = tmp.getMenu();

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode Ares = objectMapper.createArrayNode();
        for (HashMap.Entry < String, Food > entry: menuList.entrySet()) {  
            Food ftmp = entry.getValue();
            ObjectNode res = objectMapper.createObjectNode();
            
            res.put("food_id",ftmp.getFoodId());
            res.put("food_name",ftmp.getFoodName());
            res.put("food_price",ftmp.getFoodPrice());
            res.put("food_description",ftmp.getFoodDescription());
            Ares.add(res);
        } 
        return Ares;
    } 

    public String getFoodNameByID(String storeid,String foodid){
        for (HashMap.Entry < String, Store > entry: stores.entrySet()){
            Store tmp = entry.getValue();
            if(tmp.getStoreId().equals(storeid)){
                HashMap<String, Food> menuList = tmp.getMenu();
                for (HashMap.Entry < String, Food > entrymenu: menuList.entrySet()) {
                    Food ftmp = entrymenu.getValue();
                    if(ftmp.getFoodId().equals(food_id)){
                        return ftmp.getFoodName();
                    }
                }
            }
        }
        return "";
    }

    public String getStoreNameByID(String storeid){
        for (HashMap.Entry < String, Store > entry: stores.entrySet()){
            Store tmp = entry.getValue();
            if(tmp.getStoreId().equals(storeid)){
                return tmp.getStoreName();
            }
        }
        return "";
    }
}

