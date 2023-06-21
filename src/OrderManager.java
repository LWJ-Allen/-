import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class OrderManager {
    private int idCounter;
    private List<Order> orderList;

    public OrderManager(){
        idCounter = 0;
        orderList = new ArrayList<Order>();
    }

    public Order findOrder(int orderId){
        for(Order order : orderList){
            if(order.orderId == orderId){
                return order;
            }
        }
        return null;
    }

    public void createOrder(int userId, int storeId, String foodName, double price){
        idCounter += 1;
        Calendar date = Calendar.getInstance();
        Order newOrder = new Order(userId, storeId, foodName, idCounter, date, price);
        orderList.add(newOrder);
    }

    public boolean completeOrder(int orderId){
        Order tmpOrder = findOrder(orderId);
        if(tmpOrder.flag == false){
            tmpOrder.flag = true;
            return true;
        }
        else{
            //不能完成一个已经完成的订单
            return false;
        }
    }

    public List<Order> getUserOrders(int userId){
        List<Order> userOrders = new ArrayList<Order>();
        for(Order order : orderList){
            if(order.userId == userId){
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public List<Order> getStoreOrders(int storeId){
        List<Order> storeOrders = new ArrayList<Order>();
        for(Order order : orderList){
            if(order.storeId == storeId){
                storeOrders.add(order);
            }
        }
        return storeOrders;
    }
}
