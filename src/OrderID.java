public class OrderID {
    private static int Global_Order_ID_Counter = 1;

    private static double  VIP_AMOUNT = 10;

    public synchronized int getNextOrderID() {
        return Global_Order_ID_Counter++;
    }

    public int getGlobal_Order_ID_Counter(){
        return Global_Order_ID_Counter;
    }

    public static double getVipAmount() {
        return VIP_AMOUNT;
    }
}

