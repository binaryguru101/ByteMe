public interface OrderRecord {

//    Order Management
//– View pending orders
//– Update order status
//– Process refunds
//– Handle special

    void viewpendingorders();
    void UpdateOrderStatus(int OrderID,String status);
    void processRefund(int OrderID);

    void handleSpecialRequest(int OrderID, String Request);


}
