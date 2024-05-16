package termProject.vendingmachine.domain;

import lombok.Getter;
import termProject.vendingmachine.domain.drink.*;
import termProject.vendingmachine.util.Calculator;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VendingMachine {

    private List<Integer> money;
    private List<Integer> payMoney;

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

    public void updatePayMoney(int index) {
        Integer indexMoney = payMoney.get(index);
        indexMoney++;
        payMoney.set(index, indexMoney);
    }

    public String getPayMoneyResult() {
        return Calculator.payMoneyCal(getPayMoney());
    }
}
