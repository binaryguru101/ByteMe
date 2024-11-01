2023566 Ujjwal AP Assignment 3;

Assumptions: 

This is based on the first Assignment that was posted on Classroom 

Admin Functionality:

**Menu Management-->** 
1. Add New Items
2. Update Existing Items
3. Remove an item

**Order Management-->**
1. View Pending Orders 
2. Update Order Status
3. Process refunds 
4. Handle Special Requests 

**Report Generation-->**
1. View Total Revenue
2. View Each Sales
3. View Most Popular Item

**_Customer Functionality:_**

**Menu-->**
1. View all items
2. Search any items 
3. Filter by category
4. Sort by price ASC or DSC 

**Cart-->**

1. Add items
2. Remove items
3. Modify items
4. View Total
5. Reorder and look at previous orders
6. Checkout

**Order-->**

1. View Order status
2. Cancel Order
3. View Order History

Some Assumptions I made that were changed Later on:

"Admin can see the total revenue"

As every order is placed the Order_Amount is added into the Total_Revenue 
Similarly when refund is made order that amount is Subtracted from the Total_Revenue


I have decided to use Hashmaps and ArrayLists for most of my data structures they all are mostly of the same type 

Arraylists are used calculating sales and while displaying reviews ect 
They were when I only had to show a specific or list of items ie reviews or smt

However, Arraylists did not suffice when I also had to track the quantity ect because not only did I need the 
"?" object , I also needed to store quantity or ID with it hence 

implementation-wise map<int,?> where ?--> Menu,Orders,Previous_Orders,ID,Quantity ect.

i also have used syncronization in my OrderID class this is becuase i had a problem where since my OrderID was initliazed to 0, each new customer i made had its orderID starting from 0 hence if
2 customers say cus1 and cus2 gave their first orders the admin would view them both as Order 1 hence i used syncaronization and global variables to make sure 
the Gloabl ID trackers maintins the correct ID order.

_**OOPS-IMPLEMENTATION:_**

* Classes
    - Made various classes customer, AdminManage,Review,Sales ect.

* Encapsulation
    - Methods of all classes are private except some 
* Abstraction
    -User is an Abstract Class it has 3 contr Name ID pass, and it cannot be instantiated.
* Inheritance
    - Classes such as AdminManage and Customer are essentially the users of the CLI hence they both inherit from the user class .
* Polymorphism
    - Exceptions class essentially provides a base and all other exceptions handle other methods themselves while specifying its own predefined methods.

* Generic Class
    - Reviews have a generic class, and they can have both Double or String type reviews .
