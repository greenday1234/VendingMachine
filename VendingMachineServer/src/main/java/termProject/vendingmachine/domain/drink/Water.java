package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class Water {

    private int price;
    private int quantity;

    public Water() {
        this.price = 450;
        this.quantity = 10;
    }

    public void sellWater() {
        this.quantity--;
    }
}
