package termProject.vendingmachine.domain;

import lombok.Getter;
import termProject.vendingmachine.domain.drink.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VendingMachine {

    public static final List<Integer> COIN_VALUES = new ArrayList<>(List.of(10, 50, 100, 500, 1000));
    public static final List<Drink> DRINK_LIST = new ArrayList<>();

    public static List<Integer> changeMoney;
    public static List<Integer> quantityList;
    public static int remainMoney;

    private Water water;
    private Coffee coffee;
    private SportsDrink sportsDrink;
    private HighQualityCoffee highQualityCoffee;
    private Soda soda;
    private SpecialDrink specialDrink;

    public VendingMachine() {
        water = new Water();
        coffee = new Coffee();
        sportsDrink = new SportsDrink();
        soda = new Soda();
        highQualityCoffee = new HighQualityCoffee();
        specialDrink = new SpecialDrink();

        quantityList = new ArrayList<>();
        quantityInit();
        changeMoney = new ArrayList<>();
        changeMoneyInit();
        remainMoney = 0;

        drinkListInit();
    }

    private void drinkListInit() {
        DRINK_LIST.add(water);
        DRINK_LIST.add(coffee);
        DRINK_LIST.add(sportsDrink);
        DRINK_LIST.add(highQualityCoffee);
        DRINK_LIST.add(soda);
        DRINK_LIST.add(specialDrink);
    }

    /**
     * 각 동전 및 지폐의 거스름돈 수량 초기화 (10, 50, 100, 500, 1000)
     */
    private void changeMoneyInit() {
        changeMoney.add(10);
        changeMoney.add(10);
        changeMoney.add(10);
        changeMoney.add(10);
        changeMoney.add(10);
    }

    /**
     * 각 음료의 수량
     */
    private void quantityInit() {
        quantityList.add(water.getQuantity());
        quantityList.add(coffee.getQuantity());
        quantityList.add(sportsDrink.getQuantity());
        quantityList.add(soda.getQuantity());
        quantityList.add(highQualityCoffee.getQuantity());
        quantityList.add(specialDrink.getQuantity());
    }

    public void updateChangeMoney(int index) {
        changeMoney.set(index, changeMoney.get(index) + 1);
    }
}
