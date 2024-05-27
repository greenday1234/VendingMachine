package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.VendingMachineView.*;
import static util.Util.*;

public class Validation {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static void valid1000(String money) {
        if (money.equals("1000")) {
            check1000++;
        }
    }

    public static Boolean billValid(String money) {
        if (check1000 < 5) {
            return true;
        } else if(check1000 == 5 && !money.equals("1000")){
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

    public static Boolean validChangeMoney(String text) {
        for (int i = 0; i < DRINK.size(); i++) {
            if (text.equals(DRINK.get(i))) {
                if (!validRemainMoney(allPayMoney - prices[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Boolean validRemainMoney(int remainMoney) {
        for (int i = COIN_VALUES.size()-1; i >= 0; i--) {
            int coinValue = COIN_VALUES.get(i);
            int coinCount = Math.min(remainMoney / coinValue, changeMoney.get(i));  // 사용 가능한 동전 개수와 최소값 계산

            if (coinCount > 0) {
                remainMoney -= coinValue * coinCount;
            }
            if (remainMoney == 0) {
                return true;
            }
        }
        return false;
    }

    public static Boolean validQuantity(String text) {
        for (int i = 0; i < DRINK.size(); i++) {
            if (text.equals(DRINK.get(i))) {
                if (DRINK_LIST.get(i).getQuantity() <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean validMoney(String money) {
        for (int i = 0; i < DRINK.size(); i++) {
            if (money.equals(DRINK.get(i))) {
                if ((allPayMoney - prices[i]) < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean validPayBack() {
        if (allPayMoney == 0) {
            return false;
        }
        return true;
    }

    public static Boolean validPW(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}