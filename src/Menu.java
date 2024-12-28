import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    
    
    
    private int foodid;
    private double price;
    private String name;
    private int Availiablity;
    private boolean available=true;
    private String Category;

    private List<Reviews<Menu>> Reviews;


    public Menu(int foodid, double price, String name, int Availiablity, String Category) {
        this.foodid = foodid;
        this.price = price;
        this.name = name;
        this.Availiablity = Availiablity;
        this.available= this.Availiablity > 0;
        this.Reviews=new ArrayList<>();
        this.Category=Category;


    }

    public void AddReview(Reviews<Menu> review) {
        Reviews.add(review);
    }

    public void ViewReviews(){
        System.out.println(this.Reviews);
    }
    public void GUIViewReviews(VBox layout) {
        if (Reviews.isEmpty()) {
            
            Label noReviewsLabel = new Label("No reviews available for this item.");
            layout.getChildren().add(noReviewsLabel);
        } else {
            
            for (Reviews<Menu> review : Reviews) {
                
                VBox reviewBox = new VBox(5); 
                reviewBox.getStyleClass().add("review-box");


                
                Label ratingLabel = new Label("Rating: " + review.getRating() + "/5");
                ratingLabel.getStyleClass().add("review-rating");

                
                Label reviewTextLabel = new Label(review.getRev());
                reviewTextLabel.setWrapText(true);
                reviewTextLabel.getStyleClass().add("review-text");

                
                reviewBox.getChildren().addAll(ratingLabel, reviewTextLabel);

                
                Separator separator = new Separator();

                
                layout.getChildren().addAll(reviewBox, separator);
            }
        }
    }




    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailiablity() {
        return Availiablity;
    }

    public void setAvailiablity(int availiablity) {
        Availiablity = availiablity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "foodid=" + foodid +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", Availiablity=" + Availiablity +
                ", Category='" + Category + '\'' +
                '}';
    }
}
