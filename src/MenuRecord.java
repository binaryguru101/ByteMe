import java.awt.*;

public interface MenuRecord {

//– Add new items
//– Update existing items
//– Remove items
//– Modify prices
//– Update availability

    void additem(Menu item);
    void updateitem(int ID,double price,String Name,int Availiablity);
    void deleteitem(int ID);
    void modifyprice(int ID,double price);
    void modifyname(int ID,String name);


}
