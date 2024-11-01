import java.util.*;

public class Customer extends User{
    private Scanner scanner = new Scanner(System.in);
    private Map<Menu,Integer> Itemsbuying;
    private Map<Integer,Orders> PreviousHistory;

    private static int Order_IDCOunt = 0;

    public Customer(int ID, String Name, String Password) {
        super(ID, Name, Password);
        Itemsbuying = new HashMap<>();
        PreviousHistory = new HashMap<>();
    }


    public void addItem(Menu item) {
        try {
            if(item instanceof Menu){
                if (Itemsbuying.containsKey(item)) {
                    Itemsbuying.put(item, Itemsbuying.get(item) + 1);
                }else {
                    Itemsbuying.put(item, 1);
                }

            }else{
                throw new IllegalArgumentException("Invalid item type");
            }

        }catch(ClassCastException e){
            System.out.println("Items not on menu");
        }catch(IllegalArgumentException e){
            System.out.println("Invalid item type");
        }

    }

    public void removeItem(Menu item) {
        try{
            if(item instanceof Menu){
                if(Itemsbuying.containsKey(item)) {
                    Itemsbuying.remove(item);
                    System.out.println(item.getName() + " removed    ID: "+item.getFoodid());
                }else{
                    System.out.println(item.getName() + " not found");
                }

            }else{
                throw new IllegalArgumentException("Invalid item type");
            }

        }catch(ClassCastException e){
            System.out.println("Items not on menu");
        }catch(IllegalArgumentException e){
            System.out.println("Invalid item type");
        }

    }

    public void Modify(Menu item,int Quantity) {
        try{
            if(item instanceof Menu){
                if(Itemsbuying.containsKey(item)) {
                    if(Quantity==0){
                        removeItem(item);
                    }else{
                        Itemsbuying.put(item, Quantity);
                        System.out.println(item.getName() + " updated "+Quantity +"   ID: "+item.getFoodid());
                    }

                }else{
                    System.out.println(item.getName() + " not found");
                }
            }else{
                throw new IllegalArgumentException("Invalid item type");
            }
        }catch(ClassCastException e){
            System.out.println("Items not on menu");

        }catch(IllegalArgumentException e){
            System.out.println("Invalid item type");
        }

    }

    public void Total(){
        double totalamount=0.00;

        for(Menu item : Itemsbuying.keySet()) {
            System.out.println("ITEM NAME "+item.getName()+" "+ "FINAL PRICE "+item.getPrice()*Itemsbuying.get(item) + " ITEM ID: "+item.getFoodid());
            totalamount+=item.getPrice()*Itemsbuying.get(item);
        }
        System.out.println("______________________________");

        System.out.println("TOTAL AMOUNT "+totalamount);
    }

    public void Checkout(AdminManage admin,String SpecialRequest,Scanner scan){
        //go thrut he whole cart first
        Total();

        //continue prompt
        System.out.print("DO YOU WANNA CONTINUE>???");
        String Response = scan.nextLine();

        if(Objects.equals(Response, "YES") || Objects.equals(Response, "Y") || Objects.equals(Response, "Yes")){
            System.out.println("Enter Cards Details: ");
            String deets = scan.nextLine();

            System.out.println("Enter Delivery Address");
            String address = scan.nextLine();


            if(deets!=null && address!=null){
                int orderID=Order_IDCOunt++;

                Orders Order = new Orders(orderID,this.getName(),new HashMap<>(Itemsbuying),"RECIEVED");
                if(SpecialRequest!=null){
                    Order.setSpecialRequest(SpecialRequest);
                    System.out.println("Updated Request THanks");
                }
                admin.addPendingOrders(Order.getID(),Order);
                PreviousHistory.put(orderID,Order);
                CleanCart();
            }

        }

    };

    public void ReOrder(int OrderID){
       Orders Order = PreviousHistory.get(OrderID);

       if(Order!=null){
           Itemsbuying=Order.getOrderedItems();

       }else{
           System.out.println("Order not found");
       }


    }

    public void CleanCart(){
        Itemsbuying.clear();
    }

    //the Tracking Section

    public void ViewOrderStatus(int orderID){
        Orders Order = PreviousHistory.get(orderID);
        if(Order!=null){
            System.out.println("Status for "+Order.getID()+" : is "+ Order.getStatus());
        }

    }

    public void CancelOrder(int orderID,AdminManage admin){
        Orders Order = PreviousHistory.get(orderID);
        if(Order!=null){
            if(Order.getStatus().equals("Delivered")){
                System.out.println("Cannot Cancel Order");
            }
            else{
                Order.setStatus("Cancelled");
                admin.processRefund(Order.getID());
            }
        }
    }


    public Map<Menu, Integer> getItemsbuying() {
        return Itemsbuying;
    }

    public void setItemsbuying(Map<Menu, Integer> itemsbuying) {
        Itemsbuying = itemsbuying;
    }

    public Map<Integer, Orders> getPreviousHistory() {
        return PreviousHistory;
    }

    public void setPreviousHistory(Map<Integer, Orders> previousHistory) {
        PreviousHistory = previousHistory;
    }

    public void printprevhistory(Scanner scanner){
        Map<Integer,Orders> oldstuff = getPreviousHistory();
        for(Map.Entry<Integer,Orders> entry : oldstuff.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        System.out.println("________________________________");
        System.out.println("Do you want to reorder anything :");
        String reorder= scanner.nextLine();

        if(reorder.equalsIgnoreCase("yes")){
            System.out.println("Do Enter the ID of the order:");
            int id= scanner.nextInt();
            scanner.nextLine();

            ReOrder(id);
        }

    }
    public static int getOrder_IDCOunt() {
        return Order_IDCOunt;
    }

    public static void setOrder_IDCOunt(int order_IDCOunt) {
        Order_IDCOunt = order_IDCOunt;
    }




}
