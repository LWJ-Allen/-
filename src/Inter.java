import java.util.List;

public class Inter {
    private UserManager userManager;
    private StoreManager storeManager;
    private OrderManager orderManager;
    public Inter(){
        this.userManager = new UserManager();
        this.storeManager = new StoreManager();
        this.orderManager = new OrderManager();
        System.out.println("create Interface");
    }

    public void doComand(){
        //调用login，作为测试
    }

    public boolean userLogin(String userName, String password){
        return userManager.login(userName, password);
    }

    public boolean userRegister(String userName, String password){
        return userManager.register(userName, password);
    }

    public boolean createOrder(int userId, int storeId, String foodName){

        Store store = storeManager.findStoreId(storeId);
        if(store == null){
            return false;
        }
        Food food = store.findFood(foodName);
        if(food == null){
            return false;
        }
        User user = userManager.findUserId(userId);
        if(user == null){
            return false;
        }
        orderManager.createOrder(userId, storeId, foodName, food.price);
        return true;
    }

    public List<Order> getUserOrders(int userId){
        return orderManager.getUserOrders(userId);
    }
    
    public List<Food> getMenu(int storeId){
        return storeManager.getMenu(storeId);
    }

    public List<Store> getStores(){
        return storeManager.getStores();
    }

    public boolean storeLogin(String storeName, String password){
        return storeManager.login(storeName, password);
    }

    public boolean storeRegister(String storeName, String password){
        return storeRegister(storeName, password);
    }

    public List<Order> getStoreOrders(int storeId){
        return orderManager.getStoreOrders(storeId);
    }

    public boolean completeOrder(int orderId){
        return orderManager.completeOrder(orderId);
    }

    public boolean addFood(int storeId, String foodName, double price){
        return storeManager.addFood(storeId,foodName, price);
    }
}
