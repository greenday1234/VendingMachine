package termProject.vendingmachine.domain.drink;

import lombok.Getter;

@Getter
public class HighQualityCoffee {

    private int price;
    private int quantity;

    public HighQualityCoffee() {
        this.price = 700;
        this.quantity = 10;
    }

    public void sellHighQualityCoffee() {
        this.quantity--;
    }

    public void updateHighQualityCoffeePrice(int price) {
        this.price = price;
    }
}
