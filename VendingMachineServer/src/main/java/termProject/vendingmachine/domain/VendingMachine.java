package termProject.vendingmachine.domain;

import termProject.vendingmachine.domain.drink.*;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<Integer> money;

    private Water water;
    private Coffee coffee;
    private SportsDrink sportsDrink;
    private Soda soda;
    private HighQualityCoffee highQualityCoffee;
    private SpecialDrink specialDrink;

    private List<Integer> quantityList;

    public VendingMachine() {
        water = new Water();
        coffee = new Coffee();
        sportsDrink = new SportsDrink();
        soda = new Soda();
        highQualityCoffee = new HighQualityCoffee();
        specialDrink = new SpecialDrink();

        quantityList = new ArrayList<>();
        quantityInit();
        money = new ArrayList<>();
        moneyInit();
    }

    /**
     * 각 동전 및 지폐의 거스름돈 수량 초기화 (10, 50, 100, 500, 1000)
     */
    private void moneyInit() {
        money.add(10);
        money.add(10);
        money.add(10);
        money.add(10);
        money.add(10);
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
}
