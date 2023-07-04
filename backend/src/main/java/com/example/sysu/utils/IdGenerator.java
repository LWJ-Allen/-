package com.example.sysu.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

public class IdGenerator {
    private static int id = 10000000;
    private static HashMap<Integer, Integer> storeFoodIdMap = new HashMap<>();

    public static String storeIdGenerate(){
        return String.valueOf((id ++));
    }

    public static String OrderIdGenerate(){
        return String.valueOf((id ++));
    }

    public static String foodIdGenerate(int storeId) {
        if(storeFoodIdMap.containsKey(storeId)) {
            int nextFoodId = storeFoodIdMap.get(storeId) + 1;
            storeFoodIdMap.put(storeId, nextFoodId);
            return String.valueOf(storeId) + String.valueOf(nextFoodId);
        }
        else {
            storeFoodIdMap.put(storeId, 10000);
            return String.valueOf(storeId) + 10000;
        }
    }
}
