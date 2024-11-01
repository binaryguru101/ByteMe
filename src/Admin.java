//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Admin extends User{
//    private List<Orders> AvaliableOrders;
//    private Map<Integer,Menu> AvaliableMenu;
//
//    public Admin(int ID,String Name,String Password){
//        super(ID,Name,Password);
//        AvaliableOrders = new ArrayList<>();
//        AvaliableMenu = new HashMap<>();
//
//    }
//
//
//    @Override
//    public void additem(Menu item) {
//        AvaliableMenu.put(item.getFoodid(),item);
//
//    }
//
//    @Override
//    public void updateitem(int ID, double price, String Name,int Availiablty) {
//        Menu item = AvaliableMenu.get(ID);
//        if(item!=null){
//            item.setPrice(price);
//            item.setName(Name);
//            item.setAvailiablity(Availiablty);
//        }
//    }
//
//    @Override
//    public void deleteitem(int ID) {
//        AvaliableMenu.remove(ID);
//    }
//
//    @Override
//    public void modifyprice(int ID, double price) {
//        Menu item = AvaliableMenu.get(ID);
//        if(item!=null){
//            item.setPrice(price);
//        }
//    }
//
//    @Override
//    public void modifyname(int ID, String name) {
//        Menu item = AvaliableMenu.get(ID);
//        if(item!=null){
//            item.setName(name);
//        }
//    }
//
//    @Override
//    public void viewpendingorders() {
//        if(AvaliableOrders.isEmpty()){
//            System.out.println("No Orders Available");
//        }
//        for(Orders order : AvaliableOrders){
//            System.out.println(order);
//        }
//    }
//
//    @Override
//    public void UpdateOrderStatus(int OrderID, String status) {
//        boolean found = false;
//        for(Orders order : AvaliableOrders){
//            if(order.getID()==OrderID){
//                order.setStatus(status);
//                found = true;
//                break;
//            }
//        }
//        if(!found){
//            System.out.println("Order Not Found "+OrderID);
//        }
//    }
//
//    @Override
//    public void processRefund(int OrderID) {
//
//    }
//
//    @Override
//    public void handleSpecialRequest(int OrderID, String Request) {
//
//    }
//}
