package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class Soda implements Drink{

    private int price;
    private int quantity;

    public Soda() {
        this.price = 750;
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
