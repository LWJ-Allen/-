package com.example.sysu.inc;
import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    private int idCounter;
    private List<Store> storeList;

    public StoreManager(){
        System.out.println("create StoreManager");
        idCounter = 0;
        storeList = new ArrayList<Store>();

        //用于测试
        register("store1", "passworde1");
        addFood(1, "food1", 100.0);
    }

    public Store findStoreName(String storeName){
        for(Store store : storeList){
            if(store.storeName.equals(storeName)){
                return store;
            }
        }
        return null;
    }

    public Store findStoreId(int storeId){
        for(Store store : storeList){
            if(store.storeId == storeId){
                return store;
            }
        }
        return null;
    }

    public boolean login(String storeName, String password){
        Store tmpStore = findStoreName(storeName);
        if(tmpStore == null){
            //无此窗口
            return false;
        }
        return tmpStore.verify(password);
    }

    public boolean register(String storeName, String password){
        idCounter += 1;

        Store tmpStore = findStoreName(storeName);
        if(tmpStore != null){
            //窗口名字已存在
            return false;
        }
        Store newStore = new Store(idCounter, storeName, password);
        storeList.add(newStore);
        return true;
    }

    public boolean addFood(int storeId, String foodName, double price){
        Store store = findStoreId(storeId);
        return store.addFood(foodName, price);
    }

    public boolean eraseFood(int storeId, String foodName){
        Store store = findStoreId(storeId);
        return store.eraseFood(foodName);
    }

    public List<Food> getMenu(int storeId){
        Store store = findStoreId(storeId);
        return store.menu;
    }

    public List<Store> getStores(){
        return storeList;
    }
}
