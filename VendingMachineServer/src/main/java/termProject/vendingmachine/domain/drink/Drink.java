package termProject.vendingmachine.domain.drink;

public interface Drink {

    void sellDrink();

    void updatePrice(int price);

    int getPrice();

    int getQuantity();
}
