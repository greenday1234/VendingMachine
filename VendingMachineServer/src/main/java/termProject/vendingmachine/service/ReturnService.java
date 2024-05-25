package termProject.vendingmachine.service;

import static termProject.vendingmachine.VendingMachineThread.writer;
import static termProject.vendingmachine.domain.VendingMachine.*;
import static termProject.vendingmachine.domain.VendingMachine.changeMoney;
import static termProject.vendingmachine.util.Write.writeChangeMoney;

public class ReturnService {

    public static void returnRemainMoney() {
        writer.println(remainMoney);    // 거스름돈 금액 반환
        returnChange();   // 거스름돈 수량 변경
        writeChangeMoney();
    }

    private static void returnChange() {
        for (int i = COIN_VALUES.size() -1; i >= 0; i--) {
            int coinValue = COIN_VALUES.get(i);
            int coinCount = Math.min(remainMoney / coinValue, changeMoney.get(i)); // 사용 가능한 동전 개수와 최소값 계산
            if (coinCount > 0) {
                remainMoney -= coinValue * coinCount;
                changeMoney.set(i, changeMoney.get(i) - coinCount); // 사용한 동전 개수만큼 감소
                writer.println(coinValue + "원 " + coinCount + "개");
            } else {
                writer.println("");
            }
        }
    }
}
