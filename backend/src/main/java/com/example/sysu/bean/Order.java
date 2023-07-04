package com.example.sysu.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@AllArgsConstructor
@Setter
@Getter
public class Order {
    private String userId;
    private String storeId;
    private String foodId;
    private String orderId;
    private Calendar orderDate;
    private double orderPrice;
}
