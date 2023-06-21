import java.util.Calendar;

public class Order {
    public int userId;
    public int storeId;
    public String foodName; 
    public int orderId;
    public boolean flag;
    public Calendar date;
    public double price;

    Order(int userId, int storeId, String foodName, int orderId, Calendar date, double price){
        this.userId = userId;
        this.storeId = storeId;
        this.foodName = foodName;
        this.orderId = orderId;
        this.flag = false;
        this.date = date;
        this.price = price;
    }
}
