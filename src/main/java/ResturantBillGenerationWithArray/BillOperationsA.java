package ResturantBillGenerationWithArray;

public class BillOperationsA {

/*Resturant bill generation project !!
- one admin portal
- 1024 mb (Max size for JVM)
- store all the data in flat file ( .txt)
1. Owners can provide the menu details in the system like :
		- menuId ( auto generated ) (Resturant name followed by numeric )
		- menuName (Item name )
		- description of menu (Item description )
		- price
2. When costumer comes to resturant you need to the show the available menu (display menu ). Message: Welcome .........
3. Based on customer selection and for every selected item system will ask the quantity of the menu.
4. Customer can order more than one menu(item). or each menu quantity can be more than one.
5. Based on the order system will generate a bill in PDF format
	like : On the top(Header)-- Resturant name
					  		-- MenuID ItemName Price Qty Total Amount */

    static int no = 100;
    String menuName;
    String description;
    double price;
    String menuId = "ZORBA";

    public BillOperationsA(String menuName, String description, double price) {
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.menuId = menuId + (no++);
    }

    public String getMenuName() {
        return menuName;
    }

     public String getDescription() {
        return description;
    }

      public double getPrice() {
        return price;
    }

       public String getMenuId() {
        return menuId;
    }


}




