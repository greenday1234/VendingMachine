package termProject.vendingmachine.domain;

import lombok.Getter;
import termProject.vendingmachine.domain.drink.*;
import termProject.vendingmachine.util.Calculator;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VendingMachine {

    public static final List<Integer> COIN_VALUES = new ArrayList<>(List.of(10, 50, 100, 500, 1000));

    public static List<Integer> changeMoney;
    public static List<Integer> payMoney;
    public static List<Integer> quantityList;

    private Water water;
    private Coffee coffee;
    private SportsDrink sportsDrink;
    private Soda soda;
    private HighQualityCoffee highQualityCoffee;
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
        payMoney = new ArrayList<>();
        payMoneyInit();
    }

    private void payMoneyInit() {
        payMoney.add(0);
        payMoney.add(0);
        payMoney.add(0);
        payMoney.add(0);
        payMoney.add(0);
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

    public void updatePayMoney(int index) {
        Integer indexMoney = payMoney.get(index);
        indexMoney++;
        payMoney.set(index, indexMoney);
    }

    public int getPayMoneyResult() {
        return Calculator.payMoneyCal();
    }

    public void updateQuantityList(int index) {
        quantityList.set(index, water.getQuantity());
    }
}
