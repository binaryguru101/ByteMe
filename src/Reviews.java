import java.util.Date;

public class Reviews<T> {
    private T item;
    private double rating;
    private String rev;
    private Date date;
    private Customer customer;


    public Reviews(T item, double rating, String rev, Date date,Customer customer) {
        this.item = item;
        this.rating = rating;
        this.rev = rev;
        this.customer = customer;
    }



    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "item=" + item +
                ", rating=" + rating +
                ", rev='" + rev + '\''
               ;
    }
}
