package domain.drink;

public class Soda implements Drink {

    private String name;
    private int price;
    private int quantity;

    public Soda(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getDrinkName() {
        return name;
    }

    @Override
    public int getDrinkPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setDrinkName(String name) {
        this.name = name;
    }

    @Override
    public void setDrinkPrice(int price) {
        this.price = price;
    }

    @Override
    public void sellDrink() {
        this.quantity--;
    }
}
