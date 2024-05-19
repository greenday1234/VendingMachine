package termProject.vendingmachine.util;

import static termProject.vendingmachine.domain.VendingMachine.*;

public class Calculator {

    public static int payMoneyCal() {
        int ten = payMoney.get(0) * 10;
        int fif = payMoney.get(1) * 50;
        int hun = payMoney.get(2) * 100;
        int fivehun = payMoney.get(3) * 500;
        int thou = payMoney.get(4) * 1000;

        int result = ten + fif + hun + fivehun + thou;

        return result;
    }

    public static void ChangePayMoneyCal(int price) {
        for (int i = 0; i < changeMoney.size(); i++) {  // 거스름돈에 입금 금액 더하기
            changeMoney.set(i, changeMoney.get(i) + payMoney.get(i));
        }

        int result = payMoneyCal();
        remainMoney = result - price;   // 계산 후 거스름돈 가격 변경
    }

    /**
     * 해당 메소드 위치 변경 해야함!!
     */
    public static void returnChange() {
        for (int i = 0; i < COIN_VALUES.size(); i++) {
            int coinValue = COIN_VALUES.get(i);
            int coinCount = Math.min(remainMoney / coinValue, changeMoney.get(i)); // 사용 가능한 동전 개수와 최소값 계산
            if (coinCount > 0) {
                System.out.println(coinValue + "원 동전: " + coinCount + "개");
                remainMoney -= coinValue * coinCount;
                changeMoney.set(i, changeMoney.get(i) - coinCount); // 사용한 동전 개수만큼 감소
            }
        }
    }
}
