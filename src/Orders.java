import java.util.HashMap;
import java.util.Map;

public class Orders {

    private int ID;
    private String Name;
    private Map<Menu, Integer> OrderedItems; // Map to hold Menu items and their quantities
    private String Status;
    private String SpecialRequest;

    public Orders(int ID, String name, Map<Menu, Integer> orderedItems, String status) {
        this.ID = ID;
        Name = name;
        this.OrderedItems = orderedItems != null ? orderedItems : new HashMap<>(); // Initialize map if null
        this.Status = (status != null && !status.isEmpty()) ? status : "RECEIVED"; // Default status if null or empty
        this.SpecialRequest = "";
    }

    public void ViewSpecialRequest() {
        System.out.println("ID: " + this.ID+" Special Request "+this.SpecialRequest);
    }

    public double totalPrice(){
        double totalPrice = 0;
        for(Map.Entry<Menu, Integer> entry : OrderedItems.entrySet()){
            totalPrice += entry.getValue() * entry.getKey().getPrice();

        }
        return totalPrice;
    }


    // Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Map<Menu, Integer> getOrderedItems() {
        return OrderedItems;
    }

    public void GetItems(){

    }


    public void setOrderedItems(Map<Menu, Integer> orderedItems) {
        this.OrderedItems = orderedItems != null ? orderedItems : new HashMap<>(); // Handle null input
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSpecialRequest() {
        return SpecialRequest;
    }
    public void setSpecialRequest(String specialRequest) {
        SpecialRequest = specialRequest;
    }

    @Override
    public String toString() {
        return
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Status='" + Status + '\'' +
                ", SpecialRequest='" + SpecialRequest + '\'' +
                '}';
    }
}
