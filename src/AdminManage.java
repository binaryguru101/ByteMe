import java.util.*;

public class AdminManage extends User implements MenuRecord,OrderRecord{
    private Map<Integer,Orders> PendingOrders;
    private Map<Integer,Orders> CompletedOrders;
    private List<Sales> totalRevenue;
    private Map<Integer,Menu> AvaliableMenu;
    private double TotalRevenue;
    private Map<Orders,Double>priceperorder;

    private Map<Integer,Orders> ALLORDERS;

    public AdminManage(int ID, String Name, String Password) {
        super(ID, Name, Password);
        PendingOrders = new HashMap<Integer,Orders>();
        CompletedOrders = new HashMap<>();
        totalRevenue=new ArrayList<>();
        AvaliableMenu=new HashMap<>();
        TotalRevenue= 0.0;
        priceperorder=new HashMap<>();
        ALLORDERS=new HashMap<>();

    }



    @Override
    public void additem(Menu item) {
        AvaliableMenu.put(item.getFoodid(),item);

    }

    @Override
    public void updateitem(int ID, double price, String Name, int Availiablity) {
        Menu Item = AvaliableMenu.get(ID);
        if(Item!=null){
            Item.setAvailiablity(Availiablity);
            Item.setPrice(price);
            Item.setName(Name);

        }else{
            System.out.print("Item not found");
        }

    }

    @Override
    public void deleteitem(int ID) {
        if(AvaliableMenu.containsKey(ID)){
            AvaliableMenu.remove(ID);
        }
        else{
            System.out.print("Item not found");
        }

    }

    @Override
    public void modifyprice(int ID, double price) {
        Menu Item = AvaliableMenu.get(ID);
        if(Item!=null){
            Item.setPrice(price);
        }else{
            System.out.print("Item not found");
        }
    }

    @Override
    public void modifyname(int ID, String name) {
        Menu Item = AvaliableMenu.get(ID);
        if(Item!=null){
            Item.setName(name);
        }else{
            System.out.print("Item not found");
        }

    }

    @Override
    public void viewpendingorders() {
        for(Orders Order : PendingOrders.values()){
            System.out.println(Order);
        }

    }

    @Override
    public void UpdateOrderStatus(int OrderID, String status) {
        Orders Order = PendingOrders.get(OrderID);
        if(Order!=null){
            Order.setStatus(status);
        }else{
            System.out.print("Order not found");
        }
    }


    public void completedOrders(int ID){
        Orders order = PendingOrders.get(ID);
        System.out.println(order);

        if(order!=null){
            order.setStatus("Completed");
            double Money = calculateRevenue(order.getOrderedItems());

            System.out.println("Order ID "+order.getID());
            System.out.println("TOTAL ORDERED ITEMS "+order.getOrderedItems());
            System.out.println("TOTAL MONEY "+Money);



            CompletedOrders.put(order.getID(),order);
            PendingOrders.remove(order.getID());

        }

    };


    public double calculateRevenue(Map<Menu,Integer> TotalOrders){
        double totalMoney=0.0;
        System.out.println("Calculating revenue for orders: " + TotalOrders);

        for(Map.Entry<Menu,Integer> orders : TotalOrders.entrySet()){
            Menu menu = orders.getKey();
            int quantity= orders.getValue();

            if(menu!=null && quantity>0){
                totalMoney+=menu.getPrice()*quantity;
            }
        }
        return totalMoney;
    }




    @Override
    public void processRefund(int OrderID) {
//        Orders Ordering = CompletedOrders.get(OrderID) ;
        Orders Ordering = ALLORDERS.get(OrderID) ;
        if (Ordering == null) {
            System.out.println("ERROR INVALID ORDERS");
        }

        if (Ordering != null) {
            System.out.println("Order Details: " + Ordering);
            System.out.println("Ordered Items: " + Ordering.getOrderedItems());
            System.out.println(Ordering.totalPrice());
            double refundamount = Ordering.totalPrice();
            System.out.println(refundamount);
            if (refundamount != 0.0) {
                TotalRevenue -= refundamount;
                Ordering.setStatus("Cancelled AND REFUNDED");
                System.out.println("Order ID " + OrderID + " SUCESSFULLY REFUNDED");
            } else {
                System.out.print("Nothing to refund");
            }

        } else {
            System.out.println("Invalid OrderID");
        }
    }


    @Override
    public void handleSpecialRequest(int OrderID, String Request) {
        Orders Order = ALLORDERS.get(OrderID);
        if(Order!=null && !Objects.equals(Order.getSpecialRequest(), "")){
            System.out.println(Order.getSpecialRequest());
        }
    }

    public void SearchForanItem(String input){
        boolean found = false;
        for(Menu item : AvaliableMenu.values()){
            if(item.getName().toLowerCase().contains(input.toLowerCase())){
                System.out.println("ITEM FOUND ");
                System.out.println(item);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("ITEM NOT FOUND");
        }
    }

    public void FilterbyCategory(String input){
        System.out.println("Filtering by "+input);
        for(Menu item : AvaliableMenu.values()){
            if(item.getCategory().toLowerCase().contains(input.toLowerCase())){
                System.out.println(item.getCategory()+" "+item.getFoodid()+" "+item.getPrice()+" "+item.getAvailiablity());
            }
        }
    }

    public void SortbyPrice(boolean val){

       List<Map.Entry<Integer,Menu> > menulist = new ArrayList<>(AvaliableMenu.entrySet());

       menulist.sort(Comparator.comparingDouble(entry->entry.getValue().getPrice()));

       if(val){
           System.out.println("SORTED ITEMS BY PRICE ASCENDING ");
           for(Map.Entry<Integer,Menu> item : menulist){
               System.out.println(item.getValue());
           }

       }else{
           System.out.println("SORTED ITEMS BY PRICE DESCENDING ");
           for (int i = menulist.size() - 1; i >= 0; i--) {
               System.out.println(menulist.get(i).getValue());
           }
       }

    }

    public Menu Max_item(){
        Map<Menu,Integer> all_items_sold= new HashMap<>();

        for(Orders order: ALLORDERS.values()){
            Map<Menu,Integer> total_items=order.getOrderedItems();
            for(Map.Entry<Menu,Integer> item : total_items.entrySet()){
                Menu menu = item.getKey();
                int num = item.getValue();


                all_items_sold.put(menu,num);
            }

        }

        Menu max_item= null;
        int maxQ=0;

        for(Map.Entry<Menu,Integer> item : all_items_sold.entrySet()){
            if(item.getValue()>maxQ){
                maxQ=item.getValue();
                max_item=item.getKey();
            }
        }
        return max_item;
    }

    //getter setter


    public Map<Integer, Orders> getPendingOrders() {
        return PendingOrders;
    }

    public void addPendingOrders(Integer ID,Orders orderspending) {
        System.out.println("ADDED "+ID);
        PendingOrders.put(ID,orderspending);
        ALLORDERS.put(ID,orderspending);

        double Revenue = orderspending.totalPrice();
//        System.out.println(Revenue);
//        System.out.println("PREVIOUS REV"+TotalRevenue);

        Sales RevenueRecord = new Sales(new Date(),getName(),orderspending.getOrderedItems(),orderspending.getID());
        totalRevenue.add(RevenueRecord);
        TotalRevenue+=Revenue;



//        System.out.println("Revenue: "+TotalRevenue);

//        priceperorder.put(order,revenue.getRevenue());
        System.out.print("ADDED ITEM SUCCESSFULLY");


    }

    public Map<Integer, Menu> getAvaliableMenu() {
        return AvaliableMenu;
    }

    public void printmenu(){
        Map<Integer, Menu> availableMenu = getAvaliableMenu();
        for(Map.Entry<Integer, Menu> menu : availableMenu.entrySet()){
            Menu item = menu.getValue();
            System.out.println("ID: " + item.getFoodid() +
                    ", Name: " + item.getName() +
                    ", Price: $" + item.getPrice() +
                    ", Availability: " + item.getAvailiablity() +
                    ", Is Available: " + item.isAvailable());
        }
    }

    public void printRevenue(){
        System.out.println(totalRevenue);

        System.out.print("__________________________________________________");
        System.out.println("Most Popular Food Currently:");


        Menu allthings = Max_item();
        if(allthings!=null){
            System.out.println(allthings.getName());
        }else{
            System.out.println("Nothing to show");
        }
    }
    public void setAvaliableMenu(Map<Integer, Menu> avaliableMenu) {
        AvaliableMenu = avaliableMenu;
    }

    public double getTotalRevenue() {
        return TotalRevenue;
    }

    public void getSpeicalRequests(){
        for(Map.Entry<Integer,Orders> menu : PendingOrders.entrySet()){
            Orders orders = menu.getValue();
            if(!Objects.equals(orders.getSpecialRequest(), "")) {
                System.out.println("Order ID: " + orders.getID() + "REQUEST :" + orders.getSpecialRequest());
            }
        }
    }

}
