package ResturantBillGenerationWithArray;

public class PriceCalculationA {
    int number;
    double pricePerUnit;
    double totalPrice;

    public PriceCalculationA(int number, double pricePerUnit, double totalPrice) {
        this.number = number;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
    }

    public int getNumber() {
        return number;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public double getTotalPrice() {

        return totalPrice;
    }
}
