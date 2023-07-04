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
        if(stores.containsKey(name)) return false;

        stores.put(name, new Store(name, account, password,
                description, directorName, directorId, directorPhone));

        return true;

    }


}
