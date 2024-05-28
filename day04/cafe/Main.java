package cafe;

public class Main {
    public static void main(String[] args) {
        Cafe cafe = new Cafe();

        Americano americano = new Americano();
        cafe.sellCoffee(americano);

        Latte latte = new Latte();
        cafe.sellCoffee(latte);

        System.out.println(cafe.sales);
    }
}
