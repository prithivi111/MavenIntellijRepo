package ResturantBillGenerationByObjectCreation;
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

public class BillOperations {
        public static int no = 100;
        String menuName;
        String description;
        double price;
        String menuId = "ZORBA";


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(int no) {
        this.menuId = menuId +  (no++);
    }
}




