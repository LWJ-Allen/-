package com.example.sysu.inc;
import java.util.Scanner;
import java.util.List;

import com.example.sysu.inc.Inter;
public class Main {
    public static void main(String[] args) {
        Inter inter = Inter.getInstance();
        int c = 0;
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(exit == false){
            System.out.println("输入指令");
            c = scanner.nextInt();
            switch(c){
                case 0:{
                    System.out.println("注册输入用户名和密码");
                    String userName = scanner.next();
                    String password = scanner.next();
                    if(inter.userRegister(userName, password)){
                        System.out.println("成功");
                    }
                    else{
                        System.out.println("用户名已存在");
                    }
                    break;
                }
                case 1:{
                    System.out.println("输入用户名和密码");
                    String userName = scanner.next();
                    String password = scanner.next();
                    if(inter.userLogin(userName, password)){
                        System.out.println("成功");
                    }
                    else{
                        System.out.println("密码错误");
                    }
                    break;
                }
                case 2:{
                    System.out.println("开设窗口：");
                    List<Store> storeList = inter.getStores();
                    for(Store store : storeList){
                        System.out.println(store.storeId + " " + store.storeName);
                    }
                    break;
                }
                case 3:{
                    System.out.println("查看窗口，输入窗口号:");
                    int storeId = scanner.nextInt();
                    List<Food> menu = inter.getMenu(storeId);
                    for(Food food : menu){
                        System.out.println(food.foodName + " " + food.price);
                    }
                    break;
                }
                case 4:{
                    System.out.println("点单，输入用户id，窗口号和菜名");
                    int userId = scanner.nextInt();
                    int storeId = scanner.nextInt();
                    String foodName = scanner.next();
                    if(!inter.createOrder(userId, storeId, foodName)){
                        System.out.println("输入有误");
                    }
                    else{
                        System.out.println("成功");
                    }
                    break;
                }
                case 5:{
                    System.out.println("查看历史订单，输入用户id");
                    int userId = scanner.nextInt();
                    List<Order> orders = inter.getUserOrders(userId);
                    for(Order order : orders){
                        System.out.println(order.orderId + " " + order.flag);
                    }
                    break;
                }
                case 6:{
                    System.out.println("完成订单，输入订单号");
                    int orderId = scanner.nextInt();
                    inter.completeOrder(orderId);
                    break;
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}
