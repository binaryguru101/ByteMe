import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class run extends Application{
    private Map<Integer,Customer> TotalCustomers = new HashMap<>();
    private AdminManage admin1 = new AdminManage(1,"Ujj","123");
    private FileHandling fileHandling = new FileHandling();

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


    public static void main(String[] args) {

        launch(args);
    }



    public void StartPage(Stage stage) {
        Button signupCustomer = new Button("Sign Up as a Customer");
        Button loginCustomer = new Button("Login as a Customer");
        Button AdminLogin = new Button("Admin Login");
        Button exit = new Button("Exit");


        signupCustomer.setOnAction(e-> SignUpPage(stage));
        loginCustomer.setOnAction(e-> LoginPage(stage));
        AdminLogin.setOnAction(e-> AdminLogin(stage));

        exit.setOnAction(e->stage.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(signupCustomer, loginCustomer, AdminLogin, exit);

        Scene scene = new Scene(layout,800,800);
        setGlobalStylesheet(scene);
        stage.setScene(scene);
        stage.show();


    }

    public void SignUpPage(Stage stage){
        VBox layour  = new VBox(20);
        Label label = new Label("Sign Up as a Customer\n");

        Label name = new Label("Name");
        TextField text_name= new TextField();

        Label ID = new Label("ID");
        TextField text_ID= new TextField();

        Label password = new Label("Password");
        TextField text_password= new TextField();



        Button submit = new Button("Submit");
        Button back = new Button("Back");

        back.setOnAction(f->StartPage(stage));

        submit.setOnAction(e->{
            String Cus_name=text_name.getText();
            int Cus_ID= Integer.parseInt(text_ID.getText());
            String Cus_password=text_password.getText();

            Map<Integer,String> Orders = new HashMap<>();

            Customer customer = new Customer(Cus_ID,Cus_name,Cus_password,Orders);
            FileHandling.Register(Cus_name,Cus_password,Cus_ID,Orders);
            System.out.println("ADDED customer"+customer.getID()+customer.getName()+customer.getPassword());
            TotalCustomers.put(customer.getID(),customer);

            StartPage(stage);

        });

        layour.getChildren().addAll(label,name,text_name,ID,text_ID,password,text_password,submit,back);

        Scene scene = new Scene(layour,800,800);
        setGlobalStylesheet(scene);

        stage.setScene(scene);
        stage.show();

    }

    public void LoginPage(Stage stage){
        VBox layouz = new VBox(20);
        Label label = new Label("Login as a Customer\n");


        Label ID = new Label("ID");
        TextField text_ID= new TextField();

        Label password = new Label("Password");
        TextField text_password= new TextField();

        Button submit = new Button("Submit");
        Button backer = new Button("Back");
        backer.setOnAction(f->StartPage(stage));

        submit.setOnAction(e->{
            int Cus_ID=Integer.parseInt(text_ID.getText());
            String Cus_password=text_password.getText();
            Button back = new Button("Back");
            layouz.getChildren().add(back);
            back.setOnAction(f->StartPage(stage));

            boolean Logged_in;
            Customer cus = FileHandling.Login(Cus_ID,Cus_password);
            Logged_in= cus != null;

            if(Logged_in){
                System.out.println("LOGIN SUCCESSFUL"+cus.getID()+cus.getName()+cus.getPassword());
                TotalCustomers.put(cus.getID(),cus);
                customerinterface(stage,cus,admin1);
            }else{
                Label label1 = new Label("Login Failed Try again or Sign up");
                layouz.getChildren().add(label1);


            }


        });

        layouz.getChildren().addAll(label,ID,text_ID,password,text_password,submit,backer);
        Scene scene = new Scene(layouz,800,800);
        setGlobalStylesheet(scene);

        stage.setScene(scene);
        stage.show();

    }


    public void AdminLogin(Stage stage){
        VBox layouz = new VBox(20);
        Label label = new Label("Admin Login \n");

        Label ID = new Label("ID");
        TextField text_ID= new TextField();

        Label password = new Label("Password");
        TextField text_password= new TextField();

        Button submit = new Button("Submit");
        Button back = new Button("Back");

        back.setOnAction(f->StartPage(stage));

        submit.setOnAction(e->{
            int Cus_ID=Integer.parseInt(text_ID.getText());
            String Cus_password=text_password.getText();


            boolean Admin1Login=true;
            if(Cus_ID==1 && Objects.equals(Cus_password, "123")){
                System.out.println("ADMIN LOGIN SUCCESSFUL");
                showAdminMenu(stage,admin1,layouz);
            }else{
                System.out.println("ADMIN LOGIN FAILED");
                Label label1 = new Label("Admin has only 1  wrong username or pass ");
                layouz.getChildren().add(label1);

            }



        });
        layouz.getChildren().addAll(label,ID,text_ID,password,text_password,submit,back);
        Scene scene = new Scene(layouz,800,800);
        setGlobalStylesheet(scene);

        stage.setScene(scene);
        stage.show();

    }

    public  void customerinterface(Stage stage,Customer customer,AdminManage admin) {
        VBox layouz = new VBox(20);
        Label Title = new Label("Welcome to the Customer Signup Page!");

        Button Browse_Menu = new Button("Browse Menu");
        Button Cart_Menu = new Button("Cart Menu");
        Button Order_Tracking =  new Button("Order Tracking");
        Button Reviews = new Button("Reviews");
        Button Log_Out = new Button("Log Out");

        Browse_Menu.setOnAction(e->{
            MenuInterface(stage,customer,admin);
        });
        Cart_Menu.setOnAction(e->{
            CartInterface(stage,customer,admin);
        });
        Order_Tracking.setOnAction(e->{
            OrderTrackingInterface(stage,layouz,admin,customer);
        });
        Reviews.setOnAction(e->{
            ReviewInterface(stage,customer,admin,layouz);
        });
        Log_Out.setOnAction(e->{
            StartPage(stage);
        });
        layouz.getChildren().addAll(Title,Browse_Menu,Cart_Menu,Order_Tracking,Reviews,Log_Out);


        VBox layouz2 = new VBox(20);
        layouz2.setSpacing(30);

        Label totalitems = new Label("Current Total:"+customer.printTotal());
        totalitems.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label itemsInCartLabel = new Label("Items in Cart: "+customer.getItemsbuying().values());
        itemsInCartLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");


        layouz2.getChildren().addAll(totalitems,itemsInCartLabel);

        HBox layouz3 = new HBox(50);

        layouz3.getChildren().addAll(layouz,layouz2);

        Scene scene = new Scene(layouz3,800,800);
        setGlobalStylesheet(scene);

        stage.setScene(scene);
        stage.show();


    }

    public void MenuInterface(Stage stage,Customer customer,AdminManage admin){
        VBox layouz = new VBox(20);
        Label Title = new Label("MENU INTERFACE");

        Button viewAllItemsButton = new Button("1. View All Items");
        Button searchForItemButton = new Button("2. Search for an Item");
        Button filterByCategoryButton = new Button("3. Filter by Category");
        Button sortByPriceButton = new Button("4. Sort by Price");
        Button goBackButton = new Button("5. Go Back");

        viewAllItemsButton.setOnAction(e->{
            viewMenu(stage,admin,customer);
        });
        searchForItemButton.setOnAction(e->{
            searchformenu(stage,admin,customer);
        });
        filterByCategoryButton.setOnAction(e->{
            filterMenu(stage,admin,customer);
        });
        sortByPriceButton.setOnAction(e->{
            sortbyPrice(stage,admin,customer);
        });
        goBackButton.setOnAction(e->{
            customerinterface(stage,customer,admin);
        });

        layouz.getChildren().addAll(Title,viewAllItemsButton,searchForItemButton,filterByCategoryButton,sortByPriceButton,goBackButton);
        Scene scene = new Scene(layouz,800,800);
        stage.setScene(scene);
        setGlobalStylesheet(scene);
        stage.show();
    }

    public void viewMenu(Stage stage,AdminManage admin,Customer customer){
        VBox layouz = new VBox(20);
        Label Title = new Label("ALL Menu");
        layouz.getChildren().add(Title);
        Map<Integer, Menu> total_things = admin.getAvaliableMenu();
        System.out.println(total_things);
        for(Map.Entry<Integer, Menu> menu : total_things.entrySet()){
            Menu item = menu.getValue();

            Label menuLabel = new Label(
                    "ID: " + item.getFoodid() +
                            ", Name: " + item.getName() +
                            ", Price: $" + item.getPrice() +
                            ", Availability: " + item.getAvailiablity() +
                            ", Is Available: " + item.isAvailable()
            );
            layouz.getChildren().add(menuLabel);

        }
        Button Exit=new Button("Exit");
        layouz.getChildren().add(Exit);

        Exit.setOnAction(e->MenuInterface(stage,customer,admin));
        Scene scene = new Scene(layouz,800,800);
        setGlobalStylesheet(scene);

        stage.setScene(scene);
        stage.show();


    }


    public void searchformenu(Stage stage,AdminManage admin,Customer customer){
        VBox layouz = new VBox(20);
        Label Title = new Label("SEARCH FOR MENU\n");

        Label Item = new Label("ENTER THE ITEM TO SEARCH FOR");
        TextField item = new TextField();

        Button submit = new Button("SUBMIT");

        Button cancel = new Button("GO BACK");

        Label result = new Label("");

        layouz.getChildren().addAll(Title,Item,item,submit,cancel,result);

        submit.setOnAction(e->{
            String res=admin.GUISearchForanItem(item.getText());
            result.setText(res);
        });

        cancel.setOnAction(e->{
            MenuInterface(stage,customer,admin);
        });


        Scene scene = new Scene(layouz,800,800);
        stage.setScene(scene);
        setGlobalStylesheet(scene);
        stage.show();


    }




    public void sortbyPrice(Stage stage,AdminManage admin,Customer customer){
        VBox layouz = new VBox(20);
        Label Title = new Label("FILTER MENU");
        Button Ascending = new Button("Ascending");
        Button Descending = new Button("Descending");
        Button Exit = new Button("Exit");

        layouz.getChildren().add(Title);
        layouz.getChildren().add(Ascending);
        layouz.getChildren().add(Descending);
        layouz.getChildren().add(Exit);



        Ascending.setOnAction(e->{
            admin.GUISortbyPrice(true,layouz);
        });
        Descending.setOnAction(e->{
            admin.GUISortbyPrice(false,layouz);
        });

        Exit.setOnAction(e->MenuInterface(stage,customer,admin));

        Scene scene = new Scene(layouz,800,800);
        setGlobalStylesheet(scene);

        stage.setScene(scene);

        stage.show();

    }


    public void filterMenu(Stage stage,AdminManage admin,Customer customer){
        VBox layouz = new VBox(20);
        Label Title = new Label("FILTER MENU");
        TextField filter = new TextField();
        Button submit = new Button("SUBMIT");

        Button cancel = new Button("GO BACK");


        VBox results = new VBox(20);

        layouz.getChildren().addAll(Title,filter,submit,cancel,results);

        submit.setOnAction(
                e->admin.GUIFilterbyCategory(filter.getText(),results)
        );
        cancel.setOnAction(e->{
            MenuInterface(stage,customer,admin);
        });

        Scene scene = new Scene(layouz,800,800);
        stage.setScene(scene);
        setGlobalStylesheet(scene);

        stage.show();


    }


    public void CartInterface(Stage stage, Customer customer, AdminManage admin) {
        VBox layout = new VBox(20);

        Button Add = new Button("1. Add Item to Cart");
        Button Remove = new Button("2. Remove Item from Cart");
        Button Modify = new Button("3. Modify Item Quantity");
        Button Total = new Button("4. View Total");
        Button View_Prev = new Button("5. View Previous Orders");
        Button Checkout = new Button("6. Checkout");
        Button Exit = new Button("7. Exit");

        layout.getChildren().addAll(Add, Remove, Modify, Total, View_Prev, Checkout, Exit);

        Add.setOnAction(e -> {
            VBox newLayout = new VBox(20);

            TextField Items = new TextField("Enter Menu Item");
            ComboBox<String> total_items_rn=new ComboBox<>();

            total_items_rn.getItems().addAll(
                    "burger", "pizza", "fries", "soda", "salad",
                    "pasta", "sandwich", "hotdog", "iceCream",
                    "coffee", "cake", "nuggets"
            );

            total_items_rn.setPromptText("Select or Enter Menu Item");

            total_items_rn.setEditable(true);

//            layout.getChildren().add(total_items_rn);

            Button Submit = new Button("Submit");
            Button cancel = new Button("Go Back");

            newLayout.getChildren().addAll(total_items_rn, Submit, cancel);

            cancel.setOnAction(f -> {
                CartInterface(stage, customer, admin);
            });

            Submit.setOnAction(d -> {
                String selectedItem = total_items_rn.getEditor().getText();

                Menu toadd = null;
                for (Menu item : admin.getAvaliableMenu().values()) {
                    if (item.getName().equalsIgnoreCase(selectedItem)) {
                        toadd = item;
                        break;
                    }
                }

                Label resultLabel;
                if (toadd == null) {
                    resultLabel = new Label("Item NOT ADDED OR NOT FOUND.");
                } else {
                    resultLabel = new Label("Item '" + toadd.getName() + "' ADDED.");
                    customer.addItem(toadd);
                }
                newLayout.getChildren().add(resultLabel);
            });

            Scene scene = new Scene(newLayout, 800, 800);
            setGlobalStylesheet(scene);

            stage.setScene(scene);
            stage.show();
        });






        Remove.setOnAction(e -> {
            VBox newLayout = new VBox(20);

            Label removeTitle = new Label("REMOVE ITEM FROM CART");
            newLayout.getChildren().add(removeTitle);

            TextField itemToRemove = new TextField();
            itemToRemove.setPromptText("Enter item to remove");

            Button submitButton = new Button("Submit");
            Button cancelButton = new Button("Go Back");

            newLayout.getChildren().addAll(itemToRemove, submitButton, cancelButton);

            cancelButton.setOnAction(f -> {
                CartInterface(stage, customer, admin);
            });

            submitButton.setOnAction(d -> {
                String itemName = itemToRemove.getText();
                Menu toRemove = null;

                for (Menu menuItem : customer.getItemsbuying().keySet()) {
                    if (menuItem.getName().equalsIgnoreCase(itemName)) {
                        toRemove = menuItem;
                        break;
                    }
                }

                if (toRemove != null) {
                    customer.removeItem(toRemove);
                    Label successLabel = new Label("Item '" + toRemove.getName() + "' has been removed from your cart.");
                    newLayout.getChildren().add(successLabel);
                } else {
                    Label errorLabel = new Label("Item not found in the cart.");
                    newLayout.getChildren().add(errorLabel);
                }
            });

            Scene scene = new Scene(newLayout, 800, 800);
            setGlobalStylesheet(scene);

            stage.setScene(scene);
            stage.show();
        });


        Modify.setOnAction(e -> {
            VBox newLayout = new VBox(20);

            Label modifyTitle = new Label("MODIFY ITEM QUANTITY");
            newLayout.getChildren().add(modifyTitle);

            TextField itemToModify = new TextField();
            itemToModify.setPromptText("Enter item name to modify");

            TextField qtyField = new TextField();
            qtyField.setPromptText("Enter new quantity");

            Button submitButton = new Button("Submit");
            Button cancelButton = new Button("Go Back");

            newLayout.getChildren().addAll(itemToModify, qtyField, submitButton, cancelButton);

            cancelButton.setOnAction(f -> {
                CartInterface(stage, customer, admin);
            });

            submitButton.setOnAction(d -> {
                String itemName = itemToModify.getText();
                Menu toModify = null;

                for (Menu menuItem : customer.getItemsbuying().keySet()) {
                    if (menuItem.getName().equalsIgnoreCase(itemName)) {
                        toModify = menuItem;
                        break;
                    }
                }


                if (toModify != null) {
                    try {
                        int newQty = Integer.parseInt(qtyField.getText());


                        customer.Modify(toModify, newQty);

                        Label successLabel = new Label("Quantity of '" + toModify.getName() + "' has been updated to " + newQty);
                        newLayout.getChildren().add(successLabel);

                    } catch (NumberFormatException ex) {

                        Label errorLabel = new Label("Invalid quantity. Please enter a valid number.");
                        newLayout.getChildren().add(errorLabel);
                    }
                } else {

                    Label errorLabel = new Label("Item not found in the cart.");
                    newLayout.getChildren().add(errorLabel);
                }
            });

            Scene scene = new Scene(newLayout, 800, 800);
            setGlobalStylesheet(scene);

            stage.setScene(scene);

            stage.show();
        });


        Total.setOnAction(e->{
            customer.GUITotal(layout);

            Button backButton = new Button("Go Back");
            backButton.setOnAction(a->CartInterface(stage,customer,admin));
            layout.getChildren().add(backButton);

        });


        View_Prev.setOnAction(e -> {
            VBox newLayout = new VBox(20);

            
            customer.ViewLastOrders(stage, newLayout);

            
            Label confirmationLabel = new Label("Do you want to reorder?");

            
            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");

            
            yesButton.setOnAction(yesEvent -> {
                customer.Reorderingstuff(stage, newLayout);
                Button backer = new Button("Back");
                newLayout.getChildren().add(backer);
                backer.setOnAction(f->CartInterface(stage,customer,admin));
            });

            
            noButton.setOnAction(noEvent -> {
                CartInterface(stage, customer, admin); 
            });

            
            newLayout.getChildren().addAll(confirmationLabel, yesButton, noButton);

            
            Button backButton = new Button("Back to Cart");
            backButton.setOnAction(cs -> CartInterface(stage, customer, admin));
            newLayout.getChildren().add(backButton);

            
            Scene scene = new Scene(newLayout, 800, 800);
            setGlobalStylesheet(scene);
            stage.setScene(scene);
            stage.show();
        });


        Checkout.setOnAction(e -> {
            VBox newLayout = new VBox(20); 

            
            customer.GUITotal(newLayout);

            
            Label prompt = new Label("Do you want to continue?");
            Button continueButton = new Button("Continue");
            Button backButton = new Button("Go Back");

            
            newLayout.getChildren().addAll(prompt, continueButton, backButton);

            
            continueButton.setOnAction(a -> {
                newLayout.getChildren().clear(); 

                
                Label addressLabel = new Label("Enter Delivery Address:");
                TextField addressField = new TextField();
                Label cardLabel = new Label("Enter Card Details:");
                TextField cardField = new TextField();
                Label specialRequestLabel = new Label("Enter Special Requests (optional):");
                TextField specialRequestField = new TextField();
                Button submitButton = new Button("Submit");

                
                newLayout.getChildren().addAll(addressLabel, addressField, cardLabel, cardField, specialRequestLabel, specialRequestField, submitButton, backButton);

                submitButton.setOnAction(submit -> {
                    String address = addressField.getText();
                    String cardDetails = cardField.getText();

                    if (address.isEmpty() || cardDetails.isEmpty()) {
                        Label error = new Label("Please fill in all details!");
                        newLayout.getChildren().add(error); 
                    } else {
                        
                        customer.GUIcheckout(address, cardDetails, newLayout, specialRequestField, admin);
                    }
                });

                
                Scene scene = new Scene(newLayout, 800, 800);
                setGlobalStylesheet(scene);
                stage.setScene(scene);
                stage.show();
            });

            
            backButton.setOnAction(back -> {
                CartInterface(stage, customer, admin); 
            });

            
            Scene scene = new Scene(newLayout, 800, 800);
            setGlobalStylesheet(scene); 
            stage.setScene(scene); 
            stage.show();
        });


        Exit.setOnAction(e->{
            customerinterface(stage, customer, admin);
        });

        
        Scene scene = new Scene(layout, 800, 800);
setGlobalStylesheet(scene);
        setGlobalStylesheet(scene);

        stage.setScene(scene);
        stage.show();



    }

    public void OrderTrackingInterface(Stage stage, VBox layo, AdminManage admin, Customer customer) {
        VBox layout = new VBox(30);
        
        Label orderTrackingLabel = new Label("Order Tracking Interface:");

        Button viewOrderStatusButton = new Button("View Order Status");
        Button cancelOrderButton = new Button("Cancel Order");
        Button viewOrderHistoryButton = new Button("View Order History");
        Button exitButton = new Button("Exit");

        
        layout.getChildren().addAll(orderTrackingLabel, viewOrderStatusButton, cancelOrderButton, viewOrderHistoryButton, exitButton);

        viewOrderStatusButton.setOnAction(e -> {
            
            TextField orderIdField = new TextField();
            Label orderIdLabel = new Label("Enter Order ID:");

            Button viewStatusButton = new Button("View Status");

            
            layout.getChildren().addAll(orderIdLabel, orderIdField, viewStatusButton);

            
            viewStatusButton.setOnAction(viewStatus -> {
                try {
                    int orderId = Integer.parseInt(orderIdField.getText()); 

                    
                    customer.GUIViewOrderStatus(orderId, layout);

                    
                    orderIdField.clear();
                } catch (NumberFormatException ex) {
                    
                    Label errorLabel = new Label("Please enter a valid order ID.");
                    layout.getChildren().add(errorLabel);
                }
            });
        });

        
        cancelOrderButton.setOnAction(e -> {
            VBox layz= new VBox(30);
            
            Label orderIDLabel = new Label("Enter Order ID to cancel:");
            TextField orderIDField = new TextField();
            Button cancelButton = new Button("Cancel Order");
            Button backButton = new Button("Back");

            
            layz.getChildren().clear();

            
            layz.getChildren().addAll(orderIDLabel, orderIDField, cancelButton, backButton);

            cancelButton.setOnAction(cancel -> {
                try {
                    int orderID = Integer.parseInt(orderIDField.getText());  
                    
                    customer.GUIcancelOrder(orderID, admin, layz);
                } catch (NumberFormatException ex) {
                    
                    Label errorMessage = new Label("Please enter a valid Order ID.");
                    layz.getChildren().add(errorMessage);
                }
            });

            
            backButton.setOnAction(back -> {
                
                OrderTrackingInterface(stage, layout, admin,customer);
            });

            
            Scene scene = new Scene(layz, 800, 800);
setGlobalStylesheet(scene);
            stage.setScene(scene);            stage.show();
        });


        
        viewOrderHistoryButton.setOnAction(e -> {
            VBox layz= new VBox(30);
            
            layz.getChildren().clear();

            
            Label historyLabel = new Label("Order History:");
            layz.getChildren().add(historyLabel);

            
            customer.ViewLastOrders(stage, layz);

            
            Button backButton = new Button("Back");

            
            backButton.setOnAction(back -> {
                OrderTrackingInterface(stage, layout, admin,customer);  
            });

            
            layz.getChildren().add(backButton);

            
            Scene scene = new Scene(layz, 800, 800);
setGlobalStylesheet(scene);
            stage.setScene(scene);
            stage.show();
        });

        
        exitButton.setOnAction(e -> {
            
            customerinterface(stage, customer, admin);
        });

        
        Scene scene = new Scene(layout, 800, 800);
setGlobalStylesheet(scene);
        stage.setScene(scene);
        stage.show();
    }




    
    public void ReviewInterface(Stage stage, Customer customer, AdminManage admin, VBox layo) {
        VBox layout = new VBox(30);
        layout.getChildren().clear();  

        
        Label titleLabel = new Label("Review Page");
        layout.getChildren().add(titleLabel);

        
        Button addReviewButton = new Button("Add Review");
        Button viewReviewsButton = new Button("View Reviews");

        
        addReviewButton.setOnAction(e -> {
            
            Label itemLabel = new Label("Enter item name to review:");
            TextField itemField = new TextField();
            Label commentLabel = new Label("Enter your comment:");
            TextField commentField = new TextField();
            Label ratingLabel = new Label("Enter rating (1 to 5):");
            TextField ratingField = new TextField();
            Button submitReviewButton = new Button("Submit Review");

            
            layout.getChildren().addAll(itemLabel, itemField, commentLabel, commentField, ratingLabel, ratingField, submitReviewButton);

            submitReviewButton.setOnAction(submit -> {
                String itemName = itemField.getText();
                String comment = commentField.getText();
                double rating = Double.parseDouble(ratingField.getText());

                Menu itemToReview = null;
                for (Menu menuItem : admin.getAvaliableMenu().values()) {
                    if (menuItem.getName().equalsIgnoreCase(itemName)) {
                        itemToReview = menuItem;
                        break;
                    }
                }

                if (itemToReview != null) {
                    Reviews<Menu> review = new Reviews<>(itemToReview, rating, comment, new Date(), customer);
                    itemToReview.AddReview(review);
                    Label successMessage = new Label("Review Added Successfully!");
                    layout.getChildren().add(successMessage);
                } else {
                    Label errorMessage = new Label("No such item found.");
                    layout.getChildren().add(errorMessage);
                }
            });
        });

        
        viewReviewsButton.setOnAction(e -> {
            
            Label itemLabel = new Label("Enter item name to view reviews:");
            TextField itemField = new TextField();
            Button viewButton = new Button("View Reviews");

            layout.getChildren().addAll(itemLabel, itemField, viewButton);

            viewButton.setOnAction(view -> {
                String itemName = itemField.getText();

                Menu itemToView = null;
                for (Menu menuItem : admin.getAvaliableMenu().values()) {
                    if (menuItem.getName().equalsIgnoreCase(itemName)) {
                        itemToView = menuItem;
                        break;
                    }
                }

                if (itemToView != null) {
                    Label reviewsLabel = new Label("Reviews for " + itemToView.getName() + ":");
                    layout.getChildren().add(reviewsLabel);
                    itemToView.GUIViewReviews(layout);
                } else {
                    Label errorMessage = new Label("No such item found.");
                    layout.getChildren().add(errorMessage);
                }
            });
        });

        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            
            customerinterface(stage, customer, admin);  
        });

        layout.getChildren().addAll(addReviewButton, viewReviewsButton, backButton);

        
        Scene scene = new Scene(layout, 800, 800);
setGlobalStylesheet(scene);
        stage.setScene(scene);
        stage.show();
    }



    public void showAdminMenu(Stage stage, AdminManage admin, VBox layout) {
        layout.getChildren().clear();  

        
        Label titleLabel = new Label("Admin Menu");
        layout.getChildren().add(titleLabel);

        
        Button menuManagementButton = new Button("Menu Management");
        Button orderManagementButton = new Button("Order Management");
        Button reportGenerationButton = new Button("Report Generation");
        Button exitButton = new Button("Exit");

        
        layout.getChildren().addAll(menuManagementButton, orderManagementButton, reportGenerationButton, exitButton);

        
        menuManagementButton.setOnAction(e -> {
            
            showMenuManagementPage(stage, admin, layout);  
        });

        
        orderManagementButton.setOnAction(e -> {
            
            showOrderManagementPage(stage, admin, layout);  
        });

        
        reportGenerationButton.setOnAction(e -> {
            
            reportGenerationGUI(stage,admin, layout);  
        });

        
        exitButton.setOnAction(e -> {
            try {
                StartPage(stage);  
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        
        Scene scene = new Scene(layout, 800, 800);
        setGlobalStylesheet(scene);
        stage.setScene(scene);
        stage.show();
    }


    public void showMenuManagementPage(Stage stage, AdminManage admin, VBox lay) {
        VBox layout = new VBox(30);
        layout.getChildren().clear();

        Label titleLabel = new Label("Menu Management");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        layout.getChildren().add(titleLabel);

        Button addNewItemButton = new Button("Add New Item");
        Button updateExistingItemButton = new Button("Update Existing Item");
        Button removeItemButton = new Button("Remove Item");
        Button modifyPricesButton = new Button("Modify Prices");
        Button updateAvailabilityButton = new Button("Update Availability");
        Button backButton = new Button("Back to Admin Menu");

        layout.getChildren().addAll(addNewItemButton, updateExistingItemButton, removeItemButton,
                modifyPricesButton, updateAvailabilityButton, backButton);


        addNewItemButton.setOnAction(e -> {
            layout.getChildren().clear();

            Label inputLabel = new Label("Enter New Item Details");
            TextField idField = new TextField();
            idField.setPromptText("Enter Food ID");
            TextField priceField = new TextField();
            priceField.setPromptText("Enter Price");
            TextField nameField = new TextField();
            nameField.setPromptText("Enter Item Name");
            TextField availField = new TextField();
            availField.setPromptText("Enter Availability");
            TextField categoryField = new TextField();
            categoryField.setPromptText("Enter Category");

            Button addItemButton = new Button("Add Item");
            Button exitButton = new Button("Back");

            layout.getChildren().addAll(inputLabel, idField, priceField, nameField,
                    availField, categoryField, addItemButton, exitButton);

            addItemButton.setOnAction(a -> {
                try {
                    int id = Integer.parseInt(idField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    String name = nameField.getText();
                    int avail = Integer.parseInt(availField.getText());
                    String category = categoryField.getText();

                    if (name.isEmpty() || category.isEmpty()) {
                        throw new IllegalArgumentException("Name and category cannot be empty");
                    }

                    Menu newItem = new Menu(id, price, name, avail, category);
                    admin.additem(newItem);

                    Label successLabel = new Label("Item Added Successfully");
                    layout.getChildren().clear();
                    layout.getChildren().addAll(successLabel, exitButton);
                } catch (NumberFormatException ex) {
                    Label errorLabel = new Label("Please enter valid numbers for ID, price, and availability");
                    layout.getChildren().add(errorLabel);
                } catch (IllegalArgumentException ex) {
                    Label errorLabel = new Label(ex.getMessage());
                    layout.getChildren().add(errorLabel);
                }
            });

            exitButton.setOnAction(f -> showMenuManagementPage(stage, admin, layout));
        });


        updateExistingItemButton.setOnAction(e -> {
            layout.getChildren().clear();

            Label inputLabel = new Label("Enter Item ID to Update");
            TextField idField = new TextField();
            idField.setPromptText("Enter Food ID");

            Button updateButton = new Button("Update Item");
            Button exitButton = new Button("Back");

            layout.getChildren().addAll(inputLabel, idField, updateButton, exitButton);

            updateButton.setOnAction(update -> {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Menu item = admin.getAvaliableMenu().get(id);
                    if (item != null) {
                        TextField priceField = new TextField();
                        priceField.setPromptText("Enter New Price");
                        priceField.setText(String.valueOf(item.getPrice())); // Pre-fill existing value

                        TextField nameField = new TextField();
                        nameField.setPromptText("Enter New Name");
                        nameField.setText(item.getName()); // Pre-fill existing value

                        TextField availField = new TextField();
                        availField.setPromptText("Enter New Availability");
                        availField.setText(String.valueOf(item.getAvailiablity())); // Pre-fill existing value

                        TextField categoryField = new TextField();
                        categoryField.setPromptText("Enter New Category");
                        categoryField.setText(item.getCategory()); // Pre-fill existing value

                        Button saveButton = new Button("Save Changes");

                        layout.getChildren().clear();
                        layout.getChildren().addAll(
                                new Label("Current Item: " + item.getName()),
                                priceField, nameField, availField, categoryField,
                                saveButton, exitButton
                        );

                        saveButton.setOnAction(save -> {
                            try {
                                double newPrice = Double.parseDouble(priceField.getText());
                                int newAvail = Integer.parseInt(availField.getText());
                                String newName = nameField.getText();
                                String newCategory = categoryField.getText();

                                if (newName.isEmpty() || newCategory.isEmpty()) {
                                    throw new IllegalArgumentException("Name and category cannot be empty");
                                }

                                item.setPrice(newPrice);
                                item.setName(newName);
                                item.setAvailiablity(newAvail);
                                item.setCategory(newCategory);

                                Label successLabel = new Label("Item Updated Successfully");
                                layout.getChildren().clear();
                                layout.getChildren().addAll(successLabel, exitButton);
                            } catch (NumberFormatException ex) {
                                Label errorLabel = new Label("Please enter valid numbers for price and availability");
                                layout.getChildren().add(errorLabel);
                            } catch (IllegalArgumentException ex) {
                                Label errorLabel = new Label(ex.getMessage());
                                layout.getChildren().add(errorLabel);
                            }
                        });
                    } else {
                        Label errorLabel = new Label("Item not found!");
                        layout.getChildren().clear();
                        layout.getChildren().addAll(errorLabel, exitButton);
                    }
                } catch (NumberFormatException ex) {
                    Label errorLabel = new Label("Please enter a valid ID number");
                    layout.getChildren().add(errorLabel);
                }
            });

            exitButton.setOnAction(f -> showMenuManagementPage(stage, admin, layout));
        });

        removeItemButton.setOnAction(e -> {
            layout.getChildren().clear();

            Label inputLabel = new Label("Enter Item ID to Remove");
            TextField idField = new TextField();
            idField.setPromptText("Enter Food ID");

            Button removeButton = new Button("Remove Item");
            Button exitButton = new Button("Back");

            layout.getChildren().addAll(inputLabel, idField, removeButton, exitButton);

            removeButton.setOnAction(remove -> {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Menu item = admin.getAvaliableMenu().get(id);
                    if (item != null) {
                        // Show confirmation dialog
                        Label confirmLabel = new Label("Are you sure you want to remove: " + item.getName() + "?");
                        Button confirmButton = new Button("Confirm Remove");
                        Button cancelButton = new Button("Cancel");

                        layout.getChildren().clear();
                        layout.getChildren().addAll(confirmLabel, confirmButton, cancelButton);

                        confirmButton.setOnAction(confirm -> {
                            admin.deleteitem(id);
                            Label successLabel = new Label("Item Removed Successfully");
                            layout.getChildren().clear();
                            layout.getChildren().addAll(successLabel, exitButton);
                        });

                        cancelButton.setOnAction(cancel -> showMenuManagementPage(stage, admin, layout));
                    } else {
                        Label errorLabel = new Label("Item not found!");
                        layout.getChildren().clear();
                        layout.getChildren().addAll(errorLabel, exitButton);
                    }
                } catch (NumberFormatException ex) {
                    Label errorLabel = new Label("Please enter a valid ID number");
                    layout.getChildren().add(errorLabel);
                }
            });

            exitButton.setOnAction(f -> showMenuManagementPage(stage, admin, layout));
        });

        modifyPricesButton.setOnAction(e -> {
            layout.getChildren().clear();

            Label inputLabel = new Label("Enter Item ID to Modify Price");
            TextField idField = new TextField();
            idField.setPromptText("Enter Food ID");

            Button modifyButton = new Button("Modify Price");
            Button exitButton = new Button("Back");

            layout.getChildren().addAll(inputLabel, idField, modifyButton, exitButton);

            modifyButton.setOnAction(modify -> {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Menu item = admin.getAvaliableMenu().get(id);
                    if (item != null) {
                        Label currentPriceLabel = new Label(
                                String.format("Current Price for %s: $%.2f", item.getName(), item.getPrice())
                        );
                        TextField newPriceField = new TextField();
                        newPriceField.setPromptText("Enter New Price");
                        newPriceField.setText(String.valueOf(item.getPrice())); // Pre-fill current price

                        Button savePriceButton = new Button("Save Price");

                        layout.getChildren().clear();
                        layout.getChildren().addAll(currentPriceLabel, newPriceField,
                                savePriceButton, exitButton);

                        savePriceButton.setOnAction(save -> {
                            try {
                                double newPrice = Double.parseDouble(newPriceField.getText());
                                if (newPrice < 0) {
                                    throw new IllegalArgumentException("Price cannot be negative");
                                }
                                item.setPrice(newPrice);
                                Label successLabel = new Label("Price Updated Successfully");
                                layout.getChildren().clear();
                                layout.getChildren().addAll(successLabel, exitButton);
                            } catch (NumberFormatException ex) {
                                Label errorLabel = new Label("Please enter a valid price");
                                layout.getChildren().add(errorLabel);
                            } catch (IllegalArgumentException ex) {
                                Label errorLabel = new Label(ex.getMessage());
                                layout.getChildren().add(errorLabel);
                            }
                        });
                    } else {
                        Label errorLabel = new Label("Item not found!");
                        layout.getChildren().clear();
                        layout.getChildren().addAll(errorLabel, exitButton);
                    }
                } catch (NumberFormatException ex) {
                    Label errorLabel = new Label("Please enter a valid ID number");
                    layout.getChildren().add(errorLabel);
                }
            });

            exitButton.setOnAction(f -> showMenuManagementPage(stage, admin, layout));
        });

        updateAvailabilityButton.setOnAction(e -> {
            layout.getChildren().clear();

            Label inputLabel = new Label("Enter Item ID to Update Availability");
            TextField idField = new TextField();
            idField.setPromptText("Enter Food ID");

            Button updateButton = new Button("Update Availability");
            Button exitButton = new Button("Back");

            layout.getChildren().addAll(inputLabel, idField, updateButton, exitButton);

            updateButton.setOnAction(update -> {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Menu item = admin.getAvaliableMenu().get(id);
                    if (item != null) {
                        Label currentAvailLabel = new Label(
                                String.format("Current Availability for %s: %d",
                                        item.getName(), item.getAvailiablity())
                        );
                        TextField newAvailField = new TextField();
                        newAvailField.setPromptText("Enter New Availability");
                        newAvailField.setText(String.valueOf(item.getAvailiablity()));

                        Button saveAvailButton = new Button("Save Availability");

                        layout.getChildren().clear();
                        layout.getChildren().addAll(currentAvailLabel, newAvailField,
                                saveAvailButton, exitButton);

                        saveAvailButton.setOnAction(save -> {
                            try {
                                int newAvail = Integer.parseInt(newAvailField.getText());
                                if (newAvail < 0) {
                                    throw new IllegalArgumentException("Availability cannot be negative");
                                }
                                item.setAvailiablity(newAvail);
                                Label successLabel = new Label("Availability Updated Successfully");
                                layout.getChildren().clear();
                                layout.getChildren().addAll(successLabel, exitButton);
                            } catch (NumberFormatException ex) {
                                Label errorLabel = new Label("Please enter a valid number");
                                layout.getChildren().add(errorLabel);
                            } catch (IllegalArgumentException ex) {
                                Label errorLabel = new Label(ex.getMessage());
                                layout.getChildren().add(errorLabel);
                            }
                        });
                    } else {
                        Label errorLabel = new Label("Item not found!");
                        layout.getChildren().clear();
                        layout.getChildren().addAll(errorLabel, exitButton);
                    }
                } catch (NumberFormatException ex) {
                    Label errorLabel = new Label("Please enter a valid ID number");
                    layout.getChildren().add(errorLabel);
                }
            });

            exitButton.setOnAction(f -> showMenuManagementPage(stage, admin, layout));
        });

        // Back Button
        backButton.setOnAction(e -> showAdminMenu(stage, admin, layout));

        Scene scene = new Scene(layout, 800, 800);
        setGlobalStylesheet(scene);
        stage.setScene(scene);
        stage.show();
    }


//    public void showMenuManagementPage(Stage stage, AdminManage admin, VBox lay) {
//        VBox layout = new VBox(30);
//        layout.getChildren().clear();
//
//
//        Label titleLabel = new Label("Menu Management");
//        layout.getChildren().add(titleLabel);
//
//
//        Button addNewItemButton = new Button("Add New Item");
//        Button updateExistingItemButton = new Button("Update Existing Item");
//        Button removeItemButton = new Button("Remove Item");
//        Button modifyPricesButton = new Button("Modify Prices");
//        Button updateAvailabilityButton = new Button("Update Availability");
//        Button backButton = new Button("Back to Admin Menu");
//        Button hello = new Button("HELLO");
//
//        layout.getChildren().addAll(addNewItemButton, updateExistingItemButton, removeItemButton,
//                modifyPricesButton, updateAvailabilityButton, backButton);
//
//        hello.setOnAction(e->StartPage(stage));
//        addNewItemButton.setOnAction(e -> {
//
//            VBox layx= new VBox(30);
//            Label inputLabel = new Label("Enter New Item Details");
//            TextField idField = new TextField();
//            idField.setPromptText("Enter Food ID");
//            TextField priceField = new TextField();
//            priceField.setPromptText("Enter Price");
//            TextField nameField = new TextField();
//            nameField.setPromptText("Enter Item Name");
//            TextField availField = new TextField();
//            availField.setPromptText("Enter Availability");
//            TextField categoryField = new TextField();
//            categoryField.setPromptText("Enter Category");
//
//            Button addItemButton = new Button("Add Item");
//
//            Button Exit = new Button("EXIT");
//
//            layx.getChildren().clear();
//            layx.getChildren().addAll(inputLabel, idField, priceField, nameField, availField, categoryField, addItemButton,Exit);
//
//            addItemButton.setOnAction(a -> {
//
//                int id = Integer.parseInt(idField.getText());
//                double price = Double.parseDouble(priceField.getText());
//                String name = nameField.getText();
//                int avail = Integer.parseInt(availField.getText());
//                String category = categoryField.getText();
//
//                Menu newItem = new Menu(id, price, name, avail, category);
//                admin.additem(newItem);
//
//                Label successLabel = new Label("Item Added Successfully");
//                layx.getChildren().clear();
//                layx.getChildren().addAll(successLabel, backButton);
//            });
//
//            Exit.setOnAction(f-> showMenuManagementPage(stage, admin, layout));
//        });
//
//
//        updateExistingItemButton.setOnAction(e -> {
//            VBox layor= new VBox(30);
//
//            Label inputLabel = new Label("Enter Item ID to Update");
//            TextField idField = new TextField();
//            idField.setPromptText("Enter Food ID");
//
//            Button updateButton = new Button("Update Item");
//            Button Exit = new Button("EXIT");
//            layor.getChildren().clear();
//            layor.getChildren().addAll(inputLabel, idField, updateButton,Exit);
//
//            updateButton.setOnAction(update -> {
//                int id = Integer.parseInt(idField.getText());
//                Menu item = admin.getAvaliableMenu().get(id);
//                if (item != null) {
//
//                    TextField priceField = new TextField();
//                    priceField.setPromptText("Enter New Price");
//                    TextField nameField = new TextField();
//                    nameField.setPromptText("Enter New Name");
//                    TextField availField = new TextField();
//                    availField.setPromptText("Enter New Availability");
//                    TextField categoryField = new TextField();
//                    categoryField.setPromptText("Enter New Category");
//
//                    Button saveButton = new Button("Save Changes");
//
//                    layor.getChildren().clear();
//                    layor.getChildren().addAll(priceField, nameField, availField, categoryField, saveButton);
//
//                    saveButton.setOnAction(save -> {
//
//                        item.setPrice(Double.parseDouble(priceField.getText()));
//                        item.setName(nameField.getText());
//                        item.setAvailiablity(Integer.parseInt(availField.getText()));
//                        item.setCategory(categoryField.getText());
//
//                        Label successLabel = new Label("Item Updated Successfully");
//                        layout.getChildren().clear();
//                        layout.getChildren().addAll(successLabel, backButton);
//                    });
//                } else {
//                    Label errorLabel = new Label("Item not found!");
//                    layor.getChildren().clear();
//                    layor.getChildren().addAll(errorLabel, backButton);
//                }
//            });
//
//            Exit.setOnAction(f-> showMenuManagementPage(stage, admin, layout));
//        });
//
//
//        removeItemButton.setOnAction(e -> {
//            VBox layp= new VBox(30);
//
//            Label inputLabel = new Label("Enter Item ID to Remove");
//            TextField idField = new TextField();
//            idField.setPromptText("Enter Food ID");
//
//            Button removeButton = new Button("Remove Item");
//            Button Exit = new Button("EXIT");
//            layp.getChildren().clear();
//            layp.getChildren().addAll(inputLabel, idField, removeButton,Exit);
//
//            removeButton.setOnAction(remove -> {
//                int id = Integer.parseInt(idField.getText());
//                Menu item = admin.getAvaliableMenu().get(id);
//                if (item != null) {
//                    admin.deleteitem(id);
//                    Label successLabel = new Label("Item Removed Successfully");
//                    layp.getChildren().clear();
//                    layp.getChildren().addAll(successLabel, backButton);
//                } else {
//                    Label errorLabel = new Label("Item not found!");
//                    layp.getChildren().clear();
//                    layp.getChildren().addAll(errorLabel, backButton);
//                }
//            });
//
//            Exit.setOnAction(f-> showAdminMenu(stage, admin, layout));
//        });
//
//
//        modifyPricesButton.setOnAction(e -> {
//            VBox layopz = new VBox(30);
//
//
//            Label inputLabel = new Label("Enter Item ID to Modify Price");
//            TextField idField = new TextField();
//            idField.setPromptText("Enter Food ID");
//
//            Button modifyButton = new Button("Modify Price");
//            Button Exit = new Button("EXIT");
//            layopz.getChildren().clear();
//            layopz.getChildren().addAll(inputLabel, idField, modifyButton,Exit);
//
//            modifyButton.setOnAction(modify -> {
//                int id = Integer.parseInt(idField.getText());
//                Menu item = admin.getAvaliableMenu().get(id);
//                if (item != null) {
//
//                    TextField newPriceField = new TextField();
//                    newPriceField.setPromptText("Enter New Price");
//
//                    Button savePriceButton = new Button("Save Price");
//
//                    layopz.getChildren().clear();
//                    layopz.getChildren().addAll(newPriceField, savePriceButton);
//
//                    savePriceButton.setOnAction(save -> {
//                        item.setPrice(Double.parseDouble(newPriceField.getText()));
//                        Label successLabel = new Label("Price Updated Successfully");
//                        layopz.getChildren().clear();
//                        layopz.getChildren().addAll(successLabel, backButton);
//                    });
//                } else {
//                    Label errorLabel = new Label("Item not found!");
//                    layopz.getChildren().clear();
//                    layopz.getChildren().addAll(errorLabel, backButton);
//                }
//            });
//            Exit.setOnAction(f-> showAdminMenu(stage, admin, layout));
//        });
//
//
//        updateAvailabilityButton.setOnAction(e -> {
//            VBox screen = new VBox(30);
//
//            Label inputLabel = new Label("Enter Item ID to Update Availability");
//            TextField idField = new TextField();
//            idField.setPromptText("Enter Food ID");
//
//            Button updateButton = new Button("Update Availability");
//            Button Exit = new Button("EXIT");
//            screen.getChildren().clear();
//            screen.getChildren().addAll(inputLabel, idField, updateButton,Exit);
//
//            updateButton.setOnAction(update -> {
//                int id = Integer.parseInt(idField.getText());
//                Menu item = admin.getAvaliableMenu().get(id);
//                if (item != null) {
//
//                    TextField newAvailField = new TextField();
//                    newAvailField.setPromptText("Enter New Availability");
//
//                    Button saveAvailButton = new Button("Save Availability");
//
//                    screen.getChildren().clear();
//                    screen.getChildren().addAll(newAvailField, saveAvailButton);
//
//                    saveAvailButton.setOnAction(save -> {
//                        item.setAvailiablity(Integer.parseInt(newAvailField.getText()));
//                        Label successLabel = new Label("Availability Updated Successfully");
//                        screen.getChildren().clear();
//                        screen.getChildren().addAll(successLabel, backButton);
//                    });
//                } else {
//                    Label errorLabel = new Label("Item not found!");
//                    screen.getChildren().clear();
//                    screen.getChildren().addAll(errorLabel, backButton);
//                }
//            });
//
//            Exit.setOnAction(f-> showAdminMenu(stage, admin, layout));
//        });
//
//
//        backButton.setOnAction(e -> showAdminMenu(stage, admin, layout));
//
//
//        Scene scene = new Scene(layout, 800, 800);
//        setGlobalStylesheet(scene);
//        stage.setScene(scene);
//        stage.show();
//    }



    public void showOrderManagementPage(Stage stage, AdminManage admin, VBox layout) {
        layout.getChildren().clear();  

        
        Label titleLabel = new Label("Order Management");
        layout.getChildren().add(titleLabel);

        
        Button viewPendingOrdersButton = new Button("View Pending Orders");
        Button updateOrderStatusButton = new Button("Update Order Status");
        Button processRefundsButton = new Button("Process Refunds");
        Button handleSpecialRequestsButton = new Button("Handle Special Requests");
        Button backButton = new Button("Back to Admin Menu");

        
        layout.getChildren().addAll(viewPendingOrdersButton, updateOrderStatusButton,
                processRefundsButton, handleSpecialRequestsButton, backButton);

        
        viewPendingOrdersButton.setOnAction(e -> {
            admin.viewPendingOrdersGUI(admin,layout);
            Label successLabel = new Label("Pending Orders Displayed");
            
            layout.getChildren().addAll(successLabel, backButton);
        });

        
        updateOrderStatusButton.setOnAction(e -> {
            Label inputLabel = new Label("Enter Order ID to Update Status");
            TextField orderIdField = new TextField();
            orderIdField.setPromptText("Enter Order ID");

            Button updateButton = new Button("Update Status");
            layout.getChildren().clear();
            layout.getChildren().addAll(inputLabel, orderIdField, updateButton);

            updateButton.setOnAction(update -> {
                int orderID = Integer.parseInt(orderIdField.getText());

                Label statusLabel = new Label("Set Order Status to 'Complete' or Custom Status");
                TextField statusField = new TextField();
                statusField.setPromptText("Enter Status");

                Button saveButton = new Button("Save Status");
                layout.getChildren().clear();
                layout.getChildren().addAll(statusLabel, statusField, saveButton);

                saveButton.setOnAction(save -> {
                    String status = statusField.getText();
                    if ("Complete".equalsIgnoreCase(status)) {
                        admin.completedOrders(orderID);
                    } else {
                        admin.UpdateOrderStatus(orderID, status);
                    }

                    Label successLabel = new Label("Order Status Updated");
                    layout.getChildren().clear();
                    layout.getChildren().addAll(successLabel, backButton);
                });
            });
        });

        
        processRefundsButton.setOnAction(e -> {
            Label inputLabel = new Label("Enter Order ID to Process Refund");
            TextField orderIdField = new TextField();
            orderIdField.setPromptText("Enter Order ID");

            Button processButton = new Button("Process Refund");
            layout.getChildren().clear();
            layout.getChildren().addAll(inputLabel, orderIdField, processButton);

            processButton.setOnAction(process -> {
                int orderRefundID = Integer.parseInt(orderIdField.getText());
                admin.processRefund(orderRefundID);

                Label successLabel = new Label("Refund Processed");
                layout.getChildren().clear();
                layout.getChildren().addAll(successLabel, backButton);
            });
        });

        
        handleSpecialRequestsButton.setOnAction(e -> {
            admin.showSpecialRequestsGUI(admin,layout);
            Label successLabel = new Label("Special Requests Handled");
            layout.getChildren().clear();
            layout.getChildren().addAll(successLabel, backButton);
        });

        
        backButton.setOnAction(e -> showAdminMenu(stage, admin, layout));

        
        Scene scene = new Scene(layout, 800, 800);
setGlobalStylesheet(scene);
        stage.setScene(scene);
        stage.show();
    }

    public  void reportGenerationGUI(Stage stage,AdminManage admin, VBox layout) {
        layout.getChildren().clear();  

        
        Button dailySalesButton = new Button("Daily Sales Report");
        Button totalRevenueButton = new Button("Total Revenue Right Now");
        Button exitButton = new Button("Exit");

        
        TextArea reportArea = new TextArea();
        reportArea.setEditable(false);  
        reportArea.setPrefSize(600, 400);  

        
        dailySalesButton.setOnAction(e -> {
            
            String dailyReport = admin.printRevenueGUI();  
            reportArea.setText(dailyReport);  
        });

        
        totalRevenueButton.setOnAction(e -> {
            double totalRevenue = admin.getTotalRevenue();  
            reportArea.setText("Total Revenue Right Now: " + totalRevenue);
        });

        
        exitButton.setOnAction(e -> showAdminMenu(stage, admin, layout));

        
        layout.getChildren().addAll(
                new Label("Report Generation:"),
                dailySalesButton,
                totalRevenueButton,
                reportArea,
                exitButton
        );
    }




    @Override
    public void start(Stage stage) throws Exception {
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


        VBox layout = new VBox(20);
        Button button = new Button("Start");
        button.setOnAction(e->StartPage(stage));

        Button exit = new Button("Exit");
        exit.setOnAction(e->stage.close());

        layout.getChildren().addAll(button,exit);
        Scene scene = new Scene(layout,800,800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    private void setGlobalStylesheet(Scene scene) {
        scene.getStylesheets().clear();  
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles.css")).toExternalForm());
    }
}