import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Integer,Customer> TotalCustomers = new HashMap<>();

        Menu burger = new Menu(1, 2, "Burger", 1,"Snacks");
        Menu pizza = new Menu(2, 10, "Pizza", 0,"Snacks");
        Menu fries = new Menu(3, 5, "Fries", 1,"Snacks");
        Menu soda = new Menu(4, 3, "Soda", 1,"Drinks");
        Menu salad = new Menu(5, 6, "Salad", 1,"Sides");
        Menu pasta = new Menu(6, 8, "Pasta", 0,"Course");
        Menu sandwich = new Menu(7, 4, "Sandwich", 1,"Course");
        Menu hotdog = new Menu(8, 3, "Hotdog", 1,"Snack");
        Menu iceCream = new Menu(9, 4, "Ice Cream", 0,"Dessert");
        Menu coffee = new Menu(10, 2, "Coffee", 1,"Drink");
        Menu cake = new Menu(11, 7, "Cake", 0,"Dessert");
        Menu nuggets = new Menu(12, 5, "Nuggets", 1,"Sides");



        Map<Menu, Integer> orderItems1 = new HashMap<>();
        orderItems1.put(burger, 2);
        orderItems1.put(fries, 1);

        Orders order1 = new Orders(101, "John Doe", orderItems1, "Received");

        AdminManage admin1= new AdminManage(1,"Hu","GOTCHA");

        Customer customer = new Customer(102,"ujj","gotcha");

        Map <Menu, Integer> orderItems2 = new HashMap<>();

        Orders order2 = new Orders(102, "Jane Smith", orderItems2, "Received");
        orderItems2.put(hotdog, 1);
        orderItems2.put(iceCream, 1);
        orderItems2.put(coffee, 1);





        orderItems2.put(pizza, 1);
        admin1.additem(burger);
        admin1.additem(pizza);
        admin1.additem(fries);
        admin1.additem(soda);
        admin1.additem(salad);
        admin1.additem(pasta);
        admin1.additem(sandwich);
        admin1.additem(hotdog);
        admin1.additem(iceCream);
        admin1.additem(coffee);
        admin1.additem(cake);
        admin1.additem(nuggets);



        boolean running = true;

        while(running){
            System.out.println("\n1. Signup as Customer");
            System.out.println("2. Login as Customer");
            System.out.println("3. Login as Admin");
            System.out.println("4. Exit");

            System.out.print("Select an option: ");


            int choice = sc.nextInt();
            sc.nextLine();

        switch (choice) {
            case 1:
                customersignuppage(sc,TotalCustomers);
                break;
            case 2:
                customerloginpage(sc,TotalCustomers,admin1);
                break;
            case 3:
                adminloginpage(sc,admin1);
                break;
            case 4:
                System.out.println("Exit Program");
                running = false;
                break;
            default:
                System.out.println("Please choose one of the options");
        }


    }
    }


    public static void customersignuppage(Scanner scan,Map<Integer, Customer> DB) {
        System.out.println("Welcome to the Customer Signup Page!");

        System.out.print("Enter your ID: ");
        int id = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter your name: ");
        String name = scan.nextLine();

        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        Customer customer = new Customer(id,name,password);
        DB.put(id, customer);


        System.out.println("Signup successful! Welcome, " + name + "!");
    }

    public static void customerloginpage(Scanner scan,Map<Integer, Customer> DB,AdminManage admin) {
        System.out.print("Enter your ID: ");
        int ID = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        Customer customer = DB.get(ID);
        if(customer != null) {
            System.out.println("Login successful! Welcome, " + ID + "!");
            customerinterface(customer,admin);
        }else{
            System.out.println("Login failed!");
        }
    }

    public static void adminloginpage(Scanner scan,AdminManage admin) {
        System.out.print("Enter your ID");
        int ID = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        if(ID==admin.getID() ){
            System.out.println("Login successful! Welcome, " + ID + "!");
            handleadminmeny(admin);

        }else{
            System.out.println("Login failed Try again!");
        }
    }

    public static void customerinterface(Customer customer,AdminManage admin) {
        System.out.println("Welcome to the Customer Signup Page!");

        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nCustomer Interface:");
            System.out.println("1. Browse Menu");
            System.out.println("2. Cart Menu");
            System.out.println("3. Order Tracking");
            System.out.println("4. Reviews");

            System.out.println("5. Log out");

            System.out.print("Select an option: ");
            int choice = scan.nextInt();
            scan.nextLine();


            switch (choice){
                case 1:
                    menuinterface(customer,admin);
                    break;
                case 2:
                    cartinterface(customer,admin);
                    break;
                case 3:
                    ordertrackinginterface(customer,admin);
                    break;
                case 4:
                    reviewspage(customer,admin);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
            }

        }
    }
    public static void reviewspage(Customer customer,AdminManage admin){
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nReview Page:");
            System.out.println("1. Add Review");
            System.out.println("2. View Reviews");
            System.out.println("3. Go back");

            System.out.println("Choose an option from above ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the item you want to add a review for :");
                    String item = scan.nextLine();

                    Menu toadd=null;
                    for(Menu items : admin.getAvaliableMenu().values()){
                        if(items.getName().equalsIgnoreCase(item)){
                            toadd = items;
                            System.out.println("CONTINUING WITH THE REVIEW: ");
                            break;
                        }
                    }
                    if(toadd!=null){
                        System.out.print("Enter your comments :");
                        String comment = scan.nextLine();

                        System.out.print("Enter your Rating :");
                        double rating = scan.nextDouble();
                        scan.nextLine();

                        Reviews<Menu> review = new Reviews<>(toadd,rating,comment,new Date(),customer);
                        toadd.AddReview(review);
                        System.out.println("ADDED REVIEW");
                    }else{
                        System.out.println("No such item");
                    }
                    break;

                case 2:
                    System.out.print("Enter the item you want to view a review for :");
                    String review = scan.nextLine();

                    Menu viewrew=null;
                    for(Menu items : admin.getAvaliableMenu().values()){
                        if(items.getName().equalsIgnoreCase(review)){
                            viewrew = items;
                            break;
                        }
                    }
                    if(viewrew!=null){
                        System.out.print("REVIEWS ARE ");
                        viewrew.ViewReviews();

                    }else{
                        System.out.print("No such item");
                    }

                    break;

                case 3:
                    running=false;
                    break;
            }
        }
    }

    public static void ordertrackinginterface(Customer customer,AdminManage admin){

        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nOrder Tracking Interface:");
            System.out.println("1. View Order Status");
            System.out.println("2. Cancel Order");
            System.out.println("3. View Order History");
            System.out.println("4. Exit");

            System.out.print("Select an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the Order ID :");
                    int ID = scan.nextInt();
                    scan.nextLine();

                    customer.ViewOrderStatus(ID);
                    break;

                case 2:
                    System.out.print("Enter the Order ID :");
                    int OrderID = scan.nextInt();
                    scan.nextLine();

                    customer.CancelOrder(OrderID,admin);
                    break;

                case 3:
                    customer.printprevhistory(scan);
                    break;

                case 4:
                    running = false;
                    break;

            }

        }

    }

    public static void menuinterface(Customer customer,AdminManage admin) {
        Scanner scan = new Scanner(System.in);
        boolean running=true;

        while(running){
            System.out.println("\n1. View All Items");
            System.out.println("2. Search for an Item");
            System.out.println("3. Filter by Category");
            System.out.println("4. Sort by Price");
            System.out.println("5. Go back");

            System.out.println("What do you want to do ");
            int input = scan.nextInt();
            scan.nextLine();

            switch (input){
                case 1:
                    admin.printmenu();
                    break;
                case 2:
                    System.out.print("Enter the item");
                    String in = scan.nextLine();
                    admin.SearchForanItem(in);
                    break;
                case 3:
                    System.out.print("Enter the item");
                    String inp = scan.nextLine();
                    admin.FilterbyCategory(inp);
                    break;
                case 4:
                    System.out.print("SORTED ASCENDING(1) OR DESCENDING(0)  Enter 1/0 ");
                    int Ans = scan.nextInt();
                    boolean val=Ans==1;
                    admin.SortbyPrice(val);
                    break;

                case 5:
                    running = false;
                    break;
            }

    }}

    public static void cartinterface(Customer customer,AdminManage admin){

        Scanner scan = new Scanner(System.in);
        boolean running=true;

        while(running){
            System.out.println("\nCustomer Menu:");

            System.out.println("1. Add Item to Cart");
            System.out.println("2. Remove Item from Cart");
            System.out.println("3. Modify Item Quantity");
            System.out.println("4. View Total");
            System.out.println("5. View Previous Orders");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:

                    boolean conti = true;
                    while (conti) {

                        System.out.print("What do you want to add to cart: ");
                        String name = scan.nextLine();

                        Menu toadd = null;
                        for (Menu items : admin.getAvaliableMenu().values()) {
                            if (items.getName().equalsIgnoreCase(name)) {
                                toadd = items;
                                break;
                            }
                        }
                        customer.addItem(toadd);

                        System.out.print("Do you want to add more items ");
                        String ans = scan.nextLine();

                        if (ans.equalsIgnoreCase("no")) {
                            conti = false;

                        }

                    }


                    break;
                case 2:
                    System.out.print("What do you want to remove: ");
                    String item = scan.nextLine();

                    Menu toremove = null;
                    for (Menu menuitems : customer.getItemsbuying().keySet()) {
                        if (menuitems.getName().equalsIgnoreCase(item)) {
                            toremove = menuitems;
                            break;
                        }
                    }
                    if (toremove != null) {
                        customer.removeItem(toremove);
                    } else {
                        System.out.println("Item not found!");
                    }
                    break;
                case 3:
                    System.out.print("What do you want to modify: ");
                    String item2 = scan.nextLine();

                    Menu tomodify = null;
                    for (Menu menuitems : customer.getItemsbuying().keySet()) {
                        if (menuitems.getName().equalsIgnoreCase(item2)) {
                            tomodify = menuitems;
                        }
                    }

                    if (tomodify != null) {
                        System.out.print("How much do you want : ");
                        int qty = scan.nextInt();
                        customer.Modify(tomodify, qty);
                    }
                    break;
                case 4:
                    customer.Total();
                    break;
                case 5:
                    customer.printprevhistory(scan);

                    break;
                case 6:
                    System.out.print("Do you have any Special Requests for your order : ");
                    String req = scan.nextLine();

                    String request = null;
                    if (req.equalsIgnoreCase("yes")) {
                        System.out.print("Enter your Request: ");
                        request = scan.nextLine();
                    }
                    customer.Checkout(admin, request, scan);
                    break;
                case 7:
                    running = false;
                    break;

                default:
                    System.out.println("Please choose one of the options");
            }
        }

    }

    public static void handleadminmeny(AdminManage admin){
        boolean running = true;
        Scanner scan = new Scanner(System.in);


        System.out.println("\n1. Menu Management");
        System.out.println("2. Order Management");
        System.out.println("3. Report Generation");
        System.out.println("4. Exit");

        System.out.print("Select an option: ");
        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice){
            case 1:
                menumanagement(admin);
                break;
            case 2:
                ordermanagement(admin);
                break;
            case 3:
                Reportgeneration(admin);
                break;
            case 4:
                running = false;
                break;
            default:
                System.out.println("Please choose one of the options");
        }

    }

    public static void menumanagement(AdminManage admin){
        Scanner scan = new Scanner(System.in);
        boolean running=true;
        while(running){
            System.out.println("\n1. Add New Items");
            System.out.println("2. Update Existing Items");
            System.out.println("3. Remove Items");
            System.out.println("4. Modify Prices");
            System.out.println("5. Update Availiabilty");
            System.out.println("6. Exit");


            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();


            switch (choice){
                case 1:
                    System.out.print("Enter Food id ");
                    int id = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Enter item Price");
                    double price = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Enter item Name");
                    String name = scan.nextLine();

                    System.out.print("Enter Availiabilty");
                    int avail = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Enter Category");
                    String category = scan.nextLine();
                    scan.nextLine();

                    Menu Item = new Menu(id,price,name,avail,category);

                    admin.additem(Item);
                    break;
                case 2:
                    System.out.print("Enter the food ID of the item to update: ");
                    int foodId = scan.nextInt();
                    scan.nextLine();

                    Menu item = admin.getAvaliableMenu().get(foodId);
                    if(item!=null){
                        System.out.print("Enter the item Price ");
                        double price2 = scan.nextDouble();
                        scan.nextLine();

                        if(price2!=0.0){
                            item.setPrice(price2);
                        }

                        System.out.print("Enter item Name ");
                        String itemName = scan.nextLine();

                        if(itemName!=null){
                            item.setName(itemName);
                        }

                        System.out.print("Enter Availiabilty");
                        int avail2 = scan.nextInt();
                        scan.nextLine();



                        item.setAvailiablity(avail2);

                        System.out.print("Enter Category");
                        String category2 = scan.nextLine();
                        if(category2!=null){
                            item.setCategory(category2);
                        }

                    }
                    break;
                case 3:
                    System.out.print("What do you want to remove: ");
                    int toremove = scan.nextInt();
                    scan.nextLine();

                    Menu rem=admin.getAvaliableMenu().get(toremove);
                    if(rem!=null){
                        admin.deleteitem(toremove);
                    }else{
                        System.out.println("Item not found!");
                    }
                    break;
                case 4:
                    System.out.println("Enter the Item ID which you want to modify prices");
                    int modiID=scan.nextInt();
                    scan.nextLine();

                    Menu check = admin.getAvaliableMenu().get(modiID);
                    if(check!=null){
                        System.out.print("Enter the Item Price ");
                        double price2 = scan.nextDouble();
                        scan.nextLine();

                        check.setPrice(price2);
                    }
                    else {
                        System.out.println("Item not found!");
                    }
                    break;
                case 5:
                    System.out.println("Enter the Item ID which you want to update availiabilty");
                    int avail2 = scan.nextInt();
                    scan.nextLine();

                    Menu check2 = admin.getAvaliableMenu().get(avail2);

                    if(check2!=null){
                        System.out.print("Enter the Item Availability ");
                        int avail3 = scan.nextInt();
                        scan.nextLine();


                        check2.setAvailiablity(avail3);
                    }else{
                        System.out.println("Item Not found");
                    }
                    break;
                case 6:
                    running=false;
                    break;





            }
        }
    }


    public static void ordermanagement(AdminManage admin){
        Scanner scan = new Scanner(System.in);
        boolean running=true;

        while(running){
            System.out.println("\n Order Management");

            System.out.println("1. View Pending Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. Process Refunds");
            System.out.println("4. Handle Special requests");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1:
                    admin.viewpendingorders();
                    break;
                case 2:

                    System.out.print("Enter the order ID which you want to update");
                    int orderID = scan.nextInt();
                    scan.nextLine();


                    System.out.print("Set an Order to 'Complete' or Custom Status ");
                    String ans=scan.nextLine();

                    if(ans.equals("Complete")){

                        admin.completedOrders(orderID);
                    }else {

                        System.out.print("Enter the Status");
                        String status = scan.nextLine();
                        admin.UpdateOrderStatus(orderID, status);
                    }
                    break;
                case 3:
                    System.out.print("Enter the order ID which you want to process refunds for");
                    int orderRefundID = scan.nextInt();
                    scan.nextLine();

                    admin.processRefund(orderRefundID);
                    break;
                case 4:
                    System.out.print("ALL SPECIAL REQUESTS");
                    admin.getSpeicalRequests();
                    break;
                case 5:
                    running=false;
                    break;

            }
        }
    }


    public static void Reportgeneration(AdminManage admin ){
        Scanner scan = new Scanner(System.in);
        boolean running=true;

        while(running){
            System.out.println("\n Report generation");
            System.out.println("1. Daily Sales Report");
            System.out.println("2. Total Revenue Right Now");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1:
                    admin.printRevenue();
                    break;
                case 2:
                    System.out.println(admin.getTotalRevenue());
                    break;
                case 3:
                    running=false;
                    break;
            }
        }
    }
}