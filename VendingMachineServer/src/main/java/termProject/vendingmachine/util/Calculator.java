package termProject.vendingmachine.util;

import java.util.List;

public class Calculator {

    public static int payMoneyCal(List<Integer> payMoney) {
        int ten = payMoney.get(0) * 10;
        int fif = payMoney.get(1) * 50;
        int hun = payMoney.get(2) * 100;
        int fivehun = payMoney.get(3) * 500;
        int thou = payMoney.get(4) * 1000;

        int result = ten + fif + hun + fivehun + thou;

        return result;
    }

    public static int ChangePayMoneyCal() {

    }
}
