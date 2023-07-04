import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
// import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws FileNotFoundException{
        Inter inter = new Inter();
        int c = 0;
        Scanner scanner = new Scanner(new File(args[0]));
        // Scanner scanner = new Scanner(System.in);
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
                        System.out.println("注册成功");
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
                        System.out.println("登录成功");
                    }
                    else{
                        System.out.println("密码错误");
                    }
                    break;
                }
                case 2:{
                    System.out.println("所有商家：");
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
                    System.out.println("点单：输入用户id，窗口号和菜名");
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
                // 增加代码
                case 7:{
                    System.out.println("商家注册：输入用户名和密码");
                    String storeName = scanner.next();
                    String password = scanner.next();
                    if(inter.storeRegister(storeName, password)){
                        System.out.println("注册成功");
                    }
                    else{
                        System.out.println("用户名已存在");
                    }
                    break;
                }
                case 8:{
                    System.out.println("商家登入：输入用户名和密码");
                    String storeName = scanner.next();
                    String password = scanner.next();
                    if(inter.storeLogin(storeName, password)){
                        System.out.println("登录成功");
                    }
                    else{
                        System.out.println("密码错误");
                    }
                    break;
                }
                case 9:{
                    System.out.println("商家：输入商家id,菜品名，价格");
                    int storeId = scanner.nextInt();
                    String foodName = scanner.next();
                    double price = scanner.nextDouble();
                    if(inter.addFood(storeId, foodName, price)){
                        System.out.println("添加成功");
                    }
                    else{
                        System.out.println("添加失败");
                    }
                    break;
                }
                case -1:{
                    exit = true;
                    break;
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}

