package ResturantBillGenerationByObjectCreation;

public class CustomerOrder {
    String items;
    int noOfEachItems;

    public CustomerOrder(String items, int noOfEachItems) {
        this.items = items;
        this.noOfEachItems = noOfEachItems;
    }

    public String getItems() {
        return items;
    }

    public int getNoOfEachItems() {
        return noOfEachItems;
    }

}
