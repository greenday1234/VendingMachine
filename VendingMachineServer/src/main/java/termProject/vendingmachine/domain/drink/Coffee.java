package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class Coffee {

    private int price;
    private int quantity;

    public Coffee() {
        this.price = 500;
        this.quantity = 10;
    }

    public void sellCoffee(int quantity) {
        this.quantity -= quantity;
    }
}