package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class Soda {

    private int price;
    private int quantity;

    public Soda() {
        this.price = 750;
        this.quantity = 10;
    }

    public void sellSoda(int quantity) {
        this.quantity -= quantity;
    }
}
