package termProject.vendingmachine.service;

import static termProject.vendingmachine.VendingMachineThread.vendingMachine;
import static termProject.vendingmachine.VendingMachineThread.writer;
import static termProject.vendingmachine.domain.VendingMachine.COIN_VALUES;
import static termProject.vendingmachine.domain.VendingMachine.remainMoney;

public class DepositService {

    public static void moneyDeposit(String clicked) {
        for (int i = 0; i < COIN_VALUES.size(); i++) {
            if (clicked.equals(Integer.toString(COIN_VALUES.get(i)))) {
                vendingMachine.updateChangeMoney(i);
                remainMoney += COIN_VALUES.get(i);
                writer.println(remainMoney);    // 총 금액 전송
            }
        }
    }
}
