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

    public static Boolean validQuantity(String text, List<Integer> quantityList) {
        Boolean result = true;
        switch (text) {
            case "water":
                if (quantityList.get(0) <= 0) {
                    result = false;
                }
            case "coffee":
                if (quantityList.get(1) <= 0) {
                    result = false;
                }
                return true;
            case "sportsDrink":
                if (quantityList.get(2) <= 0) {
                    result = false;
                }
            case "highQualityCoffee":
                if (quantityList.get(3) <= 0) {
                    result = false;
                }
            case "soda":
                if (quantityList.get(4) <= 0) {
                    result = false;
                }
            case "specialDrink":
                if (quantityList.get(5) <= 0) {
                    result = false;
                }
        }
        return result;
    }
}