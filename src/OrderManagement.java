//import java.util.*;
//
//public class OrderManagement {
//
//    private Map<Integer,Orders> PendingOrders;
//    private List<Orders> CompletedOrders;
//    private List<Sales> totalRevenue;
//
//    public OrderManagement(){
//        PendingOrders = new HashMap<>();
//        CompletedOrders = new ArrayList<>();
//        totalRevenue = new ArrayList<>();
//
//    }
//
//    public void CompleteOrder(int OrderID){
//        Orders Order = PendingOrders.get(OrderID);
//        if(Order != null){
//            double totalamount=CalculateRevenue(Order.getOrderedItems());
//
//            Order.setStatus("Completed");
//            CompletedOrders.add(Order);
//            PendingOrders.remove(OrderID);
//
//            Sales Record = new Sales(new Date(),Order.getName(),Order.getOrderedItems(),totalamount);
//
//            totalRevenue.add(Record);
//        }
//    }
//
//    public double CalculateRevenue(Map<Menu,Integer> orders){
//        double total=0.00;
//        for(Map.Entry<Menu,Integer> entry : orders.entrySet()){
//            Menu menu = entry.getKey();
//            Integer quantity = entry.getValue();
//
//            if(menu != null && quantity != 0){
//                total+=menu.getPrice()*quantity;
//            }
//
//
//        }
//
//        return total;
//    }
//
//
//    @Override
//    public void viewpendingorders() {
//        if(PendingOrders.isEmpty()){
//            System.out.println("No pending orders");
//        }
//        else{
//            for(Orders order : PendingOrders.values()){
//                System.out.println("Status "+order.getStatus()+" OrderID "+order.getID()+" Name "+order.getName());
//            }
//        }
//    }
//
//    @Override
//    public void UpdateOrderStatus(int OrderID, String status) {
//        Orders order = PendingOrders.get(OrderID);
//        if(order != null){
//            order.setStatus(status);
//        }
//    }
//
//    @Override
//    public void processRefund(int OrderID) {
//        Orders order = PendingOrders.get(OrderID);
//        if(order != null){
//            //process refund meaning subtract it fromt he deaily expenses
//        }
//
//    }
//
//    @Override
//    public void handleSpecialRequest(int OrderID, String Request) {
//
//        Orders order = PendingOrders.get(OrderID);
//        if(order != null){
//            order.setSpecialRequest(Request);
//            System.out.println("SUCCESSFULLY ADDED THE REQUEST FOR "+order.getID());
//        }
//
//    }
//
//    public void setPendingOrders(Map<Integer, Orders> pendingOrders) {
//        PendingOrders = pendingOrders;
//    }
//
//    public void AddPendingOrders(int OrderID, Orders Order){
//        PendingOrders.put(OrderID, Order);
//    }
//}
