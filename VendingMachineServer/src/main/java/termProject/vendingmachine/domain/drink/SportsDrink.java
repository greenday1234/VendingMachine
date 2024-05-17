package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class SportsDrink {

    private int price;
    private int quantity;

    public SportsDrink() {
        this.price = 550;
        this.quantity = 10;
    }

    public void sellSportsDrink() {
        this.quantity --;
    }
}
