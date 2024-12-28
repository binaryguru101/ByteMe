import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Customer extends User{
    private Scanner scanner = new Scanner(System.in);
    private Map<Menu,Integer> Itemsbuying;
    private Map<Integer,Orders> PreviousHistory;
    Map<Integer,String> PPPreviousHistory;

    private FileHandling f;


    private String Priority;

    public OrderID orderID;

    public Customer(int ID, String Name, String Password,Map<Integer,String> PPreviousHistory) {
        super(ID, Name, Password);
        Itemsbuying = new HashMap<>();
        PreviousHistory = new HashMap<>();
        PPPreviousHistory = PPreviousHistory;
        orderID = new OrderID();
    }


    public boolean addItem(Menu item) {
        try {
            if(item != null && item.getAvailiablity()!=0){
                if (Itemsbuying.containsKey(item) ) {
                    Itemsbuying.put(item, Itemsbuying.get(item) + 1);
                }else {
                    Itemsbuying.put(item, 1);

                }
                return true;
            }else{
                throw new IllegalArgumentException("Invalid item type or Items not in stock");

            }

        }catch(ClassCastException e){
            System.out.println("Items not on menu");
            return false;
        }catch(IllegalArgumentException e){
            System.out.println("Item not availiable");
            return false;
        }


    }

    public boolean fakeaddItem(AdminManage admin, Customer customer, Scanner scan) {
        try {
            // Ask user for input
            System.out.print("What do you want to add to cart: ");
            String name = scan.nextLine();

            // Search for the item in the available menu
            Menu toadd = null;
            for (Menu items : admin.getAvaliableMenu().values()) {
                if (items.getName().equalsIgnoreCase(name)) {
                    toadd = items;
                    break;
                }
            }

            // If the item is found, add it to the customer's cart
            if (toadd != null) {
                if (Itemsbuying.containsKey(toadd)) {
                    Itemsbuying.put(toadd, Itemsbuying.get(toadd) + 1);
                } else {
                    Itemsbuying.put(toadd, 1);
                }
                customer.addItem(toadd);
                System.out.println(name + " added to cart.");
                return true;
            } else {
                System.out.println("ITEM IS NOT AVAILABLE");
                return false;
            }

        } catch (ClassCastException e) {
            System.out.println("Items not on menu");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid item type");
            return false;
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
            if(item != null){
                if(Itemsbuying.containsKey(item)) {
                    if (Quantity < 0) {
//                        throw new IllegalArgumentException("Quantity cannot be negative:");
                        System.out.println("Quantity cannot be negative:");
                        return;
                    }
                    if(Quantity==0){
                        removeItem(item);

                    }else{
                        Itemsbuying.put(item, Quantity);
                        System.out.println(item.getName() + " updated "+Quantity +"   ID: "+item.getFoodid());
                    }

                    }

                }else{
                    System.out.println(" not found");
                }

        }catch(ClassCastException e){
            System.out.println("Items not on menu");

        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }




    public String CheckModify(Menu item,int Quantity) {
            if(item != null){
                if(Itemsbuying.containsKey(item)) {
                    if (Quantity < 0) {
//                        throw new IllegalArgumentException("Quantity cannot be negative:");
                        String ans = "Quantity cannot be negative:";
                        System.out.println(ans);
                        return ans;
                    }
                    if(Quantity==0){
                        removeItem(item);
                        return "";

                    }else{
                        Itemsbuying.put(item, Quantity);
                        System.out.println(item.getName() + " updated "+Quantity +"   ID: "+item.getFoodid());
                        return "";
                    }

                }

            }else{
                String ans = "not found";
                return ans;
            }

        return "";
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

    public double printTotal(){
        double totalamount=0.00;

        for(Menu item : Itemsbuying.keySet()) {
            totalamount+=item.getPrice()*Itemsbuying.get(item);
        }
        return totalamount;

    }

    public void GUITotal(VBox layout) {
        layout.getChildren().clear();

        double totalamount = 0.00;

        for (Menu item : Itemsbuying.keySet()) {
            Label itemLabel = new Label(
                    "ITEM NAME: " + item.getName() +
                            ", FINAL PRICE: $" + (item.getPrice() * Itemsbuying.get(item)) +
                            ", ITEM ID: " + item.getFoodid()
            );
            layout.getChildren().add(itemLabel);
            totalamount += item.getPrice() * Itemsbuying.get(item);
        }

        layout.getChildren().add(new Label("______________________________"));
        layout.getChildren().add(new Label("TOTAL AMOUNT: $" + totalamount));
    }




    public double money(){
        double totalamount=0.00;

        for(Menu item : Itemsbuying.keySet()) {
            System.out.println("ITEM NAME "+item.getName()+" "+ "FINAL PRICE "+item.getPrice()*Itemsbuying.get(item) + " ITEM ID: "+item.getFoodid());
            totalamount+=item.getPrice()*Itemsbuying.get(item);
        }
        System.out.println("______________________________");

        System.out.println("TOTAL AMOUNT "+totalamount);

        return totalamount;
    }

    public void Checkout(AdminManage admin,String SpecialRequest,Scanner scan){
                Total();
        double x = money();


                System.out.print("DO YOU WANNA CONTINUE>???");
        String Response = scan.nextLine();

        if(Objects.equals(Response, "YES") || Objects.equals(Response, "Y") || Objects.equals(Response, "Yes")){
            System.out.println("Enter Cards Details: ");
            String deets = scan.nextLine();

            System.out.println("Enter Delivery Address");
            String address = scan.nextLine();


            if(deets!=null && address!=null){
                int orderID=this.orderID.getNextOrderID();
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

    public void GUIcheckout(String Addr,String Card,VBox layout,TextField special,AdminManage admin){
        int orderID=this.orderID.getNextOrderID();
        Orders Order = new Orders(orderID,this.getName(),new HashMap<>(Itemsbuying),"RECIEVED");
        if(special!=null && !special.getText().isEmpty()){
            Order.setSpecialRequest(special.getText());
        }
        String orderDetails = Order.toHistoryString();
        admin.addPendingOrders(orderID,Order);
        PreviousHistory.put(orderID,Order);
        PPPreviousHistory.put(orderID,orderDetails);
        System.out.println(PPPreviousHistory.values());
        System.out.println(PPPreviousHistory.keySet());
        FileHandling.updateCustomerOrders(this.ID,this.Name,this.Password,PPPreviousHistory);
        CleanCart();

    }

    public void ReOrder(int OrderID){
       Orders Order = PreviousHistory.get(OrderID);

       if(Order!=null){
           Itemsbuying=Order.getOrderedItems();

       }else{
           System.out.println("Order not found");
       }


    }

    public boolean GUIReOrder(int OrderID){
        Orders Order = PreviousHistory.get(OrderID);

        if(Order!=null){
            Itemsbuying=Order.getOrderedItems();
            return true;

        }else{
            System.out.println("Order not found");
            return false;
        }



    }

    public void CleanCart(){
        Itemsbuying.clear();
    }

    
    public void ViewOrderStatus(int orderID){
        Orders Order = PreviousHistory.get(orderID);
        if(Order!=null){
            System.out.println("Status for "+Order.getID()+" : is "+ Order.getStatus());
        }

    }
    public void GUIViewOrderStatus(int orderID, VBox layout) {
        Orders Order = PreviousHistory.get(orderID);

        if (Order != null) {
                        Label statusLabel = new Label("Order Status for Order ID " + Order.getID() + ": " + Order.getStatus());

                        layout.getChildren().add(statusLabel);
        } else {
                        Label errorLabel = new Label("Order ID " + orderID + " not found!");
            layout.getChildren().add(errorLabel);
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

        public void GUIcancelOrder(int orderID, AdminManage admin, VBox layout) {
        Orders order = PreviousHistory.get(orderID);
        if (order != null) {
            if (order.getStatus().equals("Delivered")) {
                                Label message = new Label("Cannot cancel order, it has already been delivered.");
                layout.getChildren().add(message);
            } else {
                                order.setStatus("Cancelled");
                admin.processRefund(orderID);


                                Label successMessage = new Label("Order " + orderID + " has been successfully cancelled.");
                layout.getChildren().add(successMessage);
            }
        } else {
                        Label errorMessage = new Label("Order ID not found.");
            layout.getChildren().add(errorMessage);
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

    public void Reorderingstuff(Stage stage, VBox layout) {
                layout.getChildren().clear();

                Label label = new Label("Enter the Order ID:");
        TextField reorderID = new TextField();
        Button submitButton = new Button("Submit");
        Label feedback = new Label();

                layout.getChildren().addAll(label, reorderID, submitButton, feedback);

                submitButton.setOnAction(e -> {
            try {
                int order = Integer.parseInt(reorderID.getText());
                boolean success = GUIReOrder(order);                 if (success) {
                    feedback.setText("Order reordered successfully!");
                } else {
                    feedback.setText("Order ID not found.");
                }
            } catch (NumberFormatException ex) {
                feedback.setText("Please enter a valid numeric Order ID.");
            }
        });
    }

    public void ViewLastOrders(Stage stage,VBox layout){
        Map<Integer,Orders> oldstuff = getPreviousHistory();
        Map<Integer,String> serialzed = getPPPreviousHistory();
        System.out.println(serialzed);

        for(Map.Entry<Integer,String> entry : serialzed.entrySet()){
            Label stuff = new Label (entry.getValue());
            layout.getChildren().add(stuff);
        }
        for(Map.Entry<Integer,Orders> entry : oldstuff.entrySet()){
            Label items = new Label(entry.getKey()+" "+entry.getValue());
            layout.getChildren().add(items);
        }
        Label newspace=new Label(" ");
        layout.getChildren().add(newspace);


    }
    public void GUIViewPreviousOrders(Stage stage, Customer customer, AdminManage admin) {
        VBox layout = new VBox();

        Map<Integer, Orders> previousOrders = customer.getPreviousHistory();

        if (previousOrders.isEmpty()) {
            Label noOrders = new Label("No previous orders found.");
            layout.getChildren().add(noOrders);
        } else {
            Label ordersLabel = new Label("Previous Orders:");
            layout.getChildren().add(ordersLabel);

            for (Map.Entry<Integer, Orders> entry : previousOrders.entrySet()) {
                Label orderLabel = new Label("Order ID: " + entry.getKey() + " -> " + entry.getValue());
                layout.getChildren().add(orderLabel);
            }

            Label reorderLabel = new Label("Do you want to reorder anything? (yes/no)");
            TextField reorderInput = new TextField();
            Button submitReorderButton = new Button("Submit");

            layout.getChildren().addAll(reorderLabel, reorderInput, submitReorderButton);

            submitReorderButton.setOnAction(e -> {
                String reorderChoice = reorderInput.getText().trim();

                if (reorderChoice.equalsIgnoreCase("yes")) {
                    Label enterIdLabel = new Label("Enter the ID of the order to reorder:");
                    TextField orderIdInput = new TextField();
                    Button reorderButton = new Button("Reorder");

                    layout.getChildren().addAll(enterIdLabel, orderIdInput, reorderButton);

                    reorderButton.setOnAction(f -> {
                        try {
                            int orderId = Integer.parseInt(orderIdInput.getText());
                            customer.ReOrder(orderId);
                            Label reorderSuccess = new Label("Order Reordered Successfully!");
                            layout.getChildren().add(reorderSuccess);
                        } catch (NumberFormatException ex) {
                            Label invalidId = new Label("Invalid order ID. Please try again.");
                            layout.getChildren().add(invalidId);
                        }
                    });
                }
            });
        }


    }

    public void GUICheckout(AdminManage admin, Customer customer, Stage stage) {
                VBox layout = new VBox();

                Label orderSummary = new Label("Your Order Summary:");
        layout.getChildren().add(orderSummary);

                double totalAmount = 0.0;
        for (Menu item : customer.getItemsbuying().keySet()) {
            double itemTotal = item.getPrice() * customer.getItemsbuying().get(item);
            totalAmount += itemTotal;
            Label itemLabel = new Label(item.getName() + " - Price: " + item.getPrice() + " x " + customer.getItemsbuying().get(item));
            layout.getChildren().add(itemLabel);
        }

                Label totalLabel = new Label("Total: " + totalAmount);
        layout.getChildren().add(totalLabel);

                Label confirmLabel = new Label("Do you want to continue?");
        layout.getChildren().add(confirmLabel);

                Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");
        layout.getChildren().addAll(confirmButton, cancelButton);

                Label cardLabel = new Label("Enter Card Details:");
        TextField cardDetails = new TextField();
        Label addressLabel = new Label("Enter Delivery Address:");
        TextField address = new TextField();

        Label specialRed= new Label("Enter any speical requests");
        TextField special = new TextField();

        layout.getChildren().addAll(cardLabel, cardDetails, addressLabel, address , specialRed, special);

                confirmButton.setOnAction(e -> {
            String cardInput = cardDetails.getText();
            String addressInput = address.getText();

            if (cardInput != null && !cardInput.isEmpty() && addressInput != null && !addressInput.isEmpty()) {
                                int orderID = this.orderID.getNextOrderID();
                Orders order = new Orders(orderID, customer.getName(), new HashMap<>(customer.getItemsbuying()), "RECEIVED");
                admin.addPendingOrders(orderID, order);


                String specialRequest = special.getText();
                order.setSpecialRequest(specialRequest);

                customer.getPreviousHistory().put(orderID, order);

                CleanCart();


                Label confirmation = new Label("Order Confirmed! Your Order ID is: " + orderID);
                layout.getChildren().add(confirmation);
                Button backToCartButton = new Button("Back to Cart");
                backToCartButton.setOnAction(f -> {
                                    });
                layout.getChildren().add(backToCartButton);


            } else {
                                Label errorMessage = new Label("Please fill in both card details and delivery address.");
                layout.getChildren().add(errorMessage);
            }
        });


    }


    public Map<Integer, String> getPPPreviousHistory() {
        return PPPreviousHistory;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }
}
