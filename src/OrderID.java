public class OrderID {
    private static int Global_Order_ID_Counter = 1;

    public synchronized int getNextOrderID() {
        return Global_Order_ID_Counter++;
    }

    public int getGlobal_Order_ID_Counter(){
        return Global_Order_ID_Counter;
    }
}

