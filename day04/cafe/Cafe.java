package cafe;

public class Cafe {
    Integer sales;

    public Cafe() {
        this.sales = 0;
    }

    void sellCoffee(Coffee coffee) {
        sales = sales + coffee.getPrice();
    }
}