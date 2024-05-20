package validate;

import static View.VendingMachineView.*;

public class Validation {

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
        Boolean result = true;
        switch (text) {
            case "water":
                if (!validRemainMoney(allPayMoney - 450)) {
                    result =  false;
                }
                break;
            case "coffee":
                if (!validRemainMoney(allPayMoney - 500)) {
                    result =  false;
                }
                break;
            case "sportsDrink":
                if (!validRemainMoney(allPayMoney - 550)) {
                    result =  false;
                }
                break;
            case "highQualityCoffee":
                if (!validRemainMoney(allPayMoney - 700)) {
                    result =  false;
                }
                break;
            case "soda":
                if (!validRemainMoney(allPayMoney - 750)) {
                    result =  false;
                }
                break;
            case "specialDrink":
                if (!validRemainMoney(allPayMoney - 800)) {
                    result =  false;
                }
                break;
        }
        return result;
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
        Boolean result = true;
        switch (text) {
            case "water":
                if (quantityList.get(0) <= 0) {
                    result = false;
                }
                break;
            case "coffee":
                if (quantityList.get(1) <= 0) {
                    result = false;
                }
                break;
            case "sportsDrink":
                if (quantityList.get(2) <= 0) {
                    result = false;
                }
                break;
            case "highQualityCoffee":
                if (quantityList.get(3) <= 0) {
                    result = false;
                }
                break;
            case "soda":
                if (quantityList.get(4) <= 0) {
                    result = false;
                }
                break;
            case "specialDrink":
                if (quantityList.get(5) <= 0) {
                    result = false;
                }
                break;
        }
        return result;
    }

    public static Boolean validMoney(String money) {
        switch (money) {
            case "water":
                if ((allPayMoney-450) < 0) {
                    return false;
                }
                break;
            case "coffee":
                if ((allPayMoney-500) < 0) {
                    return false;
                }
                break;
            case "sportsDrink":
                if ((allPayMoney-550) < 0) {
                    return false;
                }
                break;
            case "highQualityCoffee":
                if ((allPayMoney-700) < 0) {
                    return false;
                }
                break;
            case "soda":
                if ((allPayMoney-750) < 0) {
                    return false;
                }
                break;
            case "specialDrink":
                if ((allPayMoney-800) < 0) {
                    return false;
                }
                break;
        }
        return true;
    }

    public static Boolean validPayBack() {
        if (allPayMoney == 0) {
            return false;
        }
        return true;
    }
}