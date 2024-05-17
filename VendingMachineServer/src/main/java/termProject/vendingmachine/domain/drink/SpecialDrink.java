package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class SpecialDrink {

    private int price;
    private int quantity;

    public SpecialDrink() {
        this.price = 800;
        this.quantity = 10;
    }

    public void sellSpecialDrink() {
        this.quantity--;
    }
}
