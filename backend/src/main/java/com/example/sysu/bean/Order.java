package com.example.sysu.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
public class Order {
    private String userId;
    private String storeId;
    private String foodId;
    private String orderId;
    private String orderDate;
    private double orderPrice;
}
