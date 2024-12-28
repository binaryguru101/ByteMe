import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OutofStock {
    Map<Integer,Customer> TotalCustomers = new HashMap<>();
    AdminManage admin1= new AdminManage(1,"Hu","GOTCHA");
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


    @BeforeEach
    void setup(){

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

        Customer customer = new Customer(202,"Ujjwal","1234",new HashMap<>());
        TotalCustomers.put(202,customer);

    }


        @Test
        void OutofStockItems() {

            Customer customer = TotalCustomers.get(202);

            boolean res = customer.addItem(pizza);
            assertFalse(res,"Item not availiable ");
//            assertTrue(res,"Item not availiable ");


        }

        @Test
        void inStock(){
            Customer customer = TotalCustomers.get(202);
            boolean res = customer.addItem(burger);
            assertTrue(res,"Item is  availiable and added");

        }

        @Test
        void DoesNotGetProcessed(){
            Customer customer = TotalCustomers.get(202);
            customer.addItem(pizza);

            assertEquals(0,customer.getItemsbuying().size(),"Items not processes");
        }

    @Test
    void DoesGetProcessed(){
        Customer customer = TotalCustomers.get(202);
        customer.addItem(burger);

        assertEquals(1,customer.getItemsbuying().size(),"Items are processed");
    }

    @Test
    void illegalItem(){
        Customer customer = TotalCustomers.get(202);
        boolean res =customer.addItem(null);
        assertFalse(res);

    }
}
