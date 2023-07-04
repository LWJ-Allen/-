package com.example.sysu.repository;

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
        ArrayNode Ares; 
        for (HashMap.Entry < String, Store > entry: stores.entrySet()) {  
            Store tmp = entry.getValue();
            ObjectMapper objectMapper = new ObjectMapper();
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
}
