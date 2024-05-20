package domain;

public class Drink {

    private String water;
    private int waterPrice;
    private String coffee;
    private int coffeePrice;
    private String sportsDrink;
    private int sportsDrinkPrice;
    private String highQualityCoffee;
    private int highQualityCoffeePrice;
    private String soda;
    private int sodaPrice;
    private String specialDrink;
    private int specialDrinkPrice;

    public Drink(String water, int waterPrice, String coffee,
                 int coffeePrice, String sportsDrink, int sportsDrinkPrice,
                 String highQualityCoffee, int highQualityCoffeePrice, String soda,
                 int sodaPrice, String specialDrink, int specialDrinkPrice) {
        this.water = water;
        this.waterPrice = waterPrice;
        this.coffee = coffee;
        this.coffeePrice = coffeePrice;
        this.sportsDrink = sportsDrink;
        this.sportsDrinkPrice = sportsDrinkPrice;
        this.highQualityCoffee = highQualityCoffee;
        this.highQualityCoffeePrice = highQualityCoffeePrice;
        this.soda = soda;
        this.sodaPrice = sodaPrice;
        this.specialDrink = specialDrink;
        this.specialDrinkPrice = specialDrinkPrice;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public int getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(int waterPrice) {
        this.waterPrice = waterPrice;
    }

    public String getCoffee() {
        return coffee;
    }

    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }

    public int getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(int coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public String getSportsDrink() {
        return sportsDrink;
    }

    public void setSportsDrink(String sportsDrink) {
        this.sportsDrink = sportsDrink;
    }

    public int getSportsDrinkPrice() {
        return sportsDrinkPrice;
    }

    public void setSportsDrinkPrice(int sportsDrinkPrice) {
        this.sportsDrinkPrice = sportsDrinkPrice;
    }

    public String getHighQualityCoffee() {
        return highQualityCoffee;
    }

    public void setHighQualityCoffee(String highQualityCoffee) {
        this.highQualityCoffee = highQualityCoffee;
    }

    public int getHighQualityCoffeePrice() {
        return highQualityCoffeePrice;
    }

    public void setHighQualityCoffeePrice(int highQualityCoffeePrice) {
        this.highQualityCoffeePrice = highQualityCoffeePrice;
    }

    public String getSoda() {
        return soda;
    }

    public void setSoda(String soda) {
        this.soda = soda;
    }

    public int getSodaPrice() {
        return sodaPrice;
    }

    public void setSodaPrice(int sodaPrice) {
        this.sodaPrice = sodaPrice;
    }

    public String getSpecialDrink() {
        return specialDrink;
    }

    public void setSpecialDrink(String specialDrink) {
        this.specialDrink = specialDrink;
    }

    public int getSpecialDrinkPrice() {
        return specialDrinkPrice;
    }

    public void setSpecialDrinkPrice(int specialDrinkPrice) {
        this.specialDrinkPrice = specialDrinkPrice;
    }
}
