import java.util.HashMap;

public class Dummy {
    private FileHandling f;
    public static void main(String[] args) {

        Orders order1 = new Orders(1, "Ujjwak",new HashMap<Menu,Integer>(),"RUNNING" );
        System.out.println("Hello World!");
        String username = "Ujjwal";
        String password = "password123";

        int customerID = 101;
        Customer customer = new Customer(customerID, username, password, new HashMap<>());



    }
}
