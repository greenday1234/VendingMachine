package domain.drink;

public interface Drink {

    String getDrinkName();
    int getDrinkPrice();
    int getQuantity();

    void setDrinkName(String name);
    void setDrinkPrice(int price);
    void sellDrink();
}
