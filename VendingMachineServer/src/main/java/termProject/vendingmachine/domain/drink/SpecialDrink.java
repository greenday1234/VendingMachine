package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class SpecialDrink implements Drink{

    private int price;
    private int quantity;

    public SpecialDrink() {
        this.price = 800;
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
