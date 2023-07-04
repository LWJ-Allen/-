package com.example.sysu.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Food {
    private String foodId;
    private String foodName;
    private double foodPrice;

    private String foodDescription;

}
