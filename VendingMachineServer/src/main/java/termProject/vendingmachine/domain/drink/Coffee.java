package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class Coffee implements Drink{

    private int price;
    private int quantity;

    public Coffee() {
        this.price = 500;
        this.quantity = 10;
    }

    @Override
    public void sellDrink() {
        this.quantity--;
    }

    @Override
    public void updatePrice(int price) {
        this.price = price;
    }
}
