package validate;

import java.util.List;

public class Validation {

    public static Boolean billValid(List<Integer> payMoney, String money) {
        if (payMoney.get(4) < 5) {
            return true;
        } else if(payMoney.get(4) == 5 && !money.equals("1000")){
            return true;
        }
        return false;
    }


    public static Boolean overMoney(String payMoney, String money) {
        String[] split = payMoney.split(" ");
        int addMoney = Integer.parseInt(money);
        int result = Integer.parseInt(split[0]);

        if (result + addMoney > 7000) {
            return false;
        }
        return true;
    }

    public static Boolean quantityCheck(String quantity) {
        String[] split = quantity.split(" ");
        int result = Integer.parseInt(split[0]);
        if (result == 0) {
            return false;
        }
        return true;
    }
}