package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class SportsDrink implements Drink {

    private int price;
    private int quantity;

    public SportsDrink() {
        this.price = 550;
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
