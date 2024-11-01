import java.util.Date;
import java.util.Map;

public class Sales {

    private Date date;
    private String customerName;
    private Map<Menu,Integer> totalsold;
    private double revenue;

    public Sales(Date date, String customerName, Map<Menu, Integer> totalsold, double revenue) {
        this.date = date;
        this.customerName = customerName;
        this.totalsold = totalsold;
        this.revenue = revenue;
    }


    @Override
    public String toString() {
        return
                "date=" + date +
                ", customerName='" + customerName + '\'' +
                ", totalsold=" + totalsold +
                ", revenue=" + revenue +
                '}';
    }
}
