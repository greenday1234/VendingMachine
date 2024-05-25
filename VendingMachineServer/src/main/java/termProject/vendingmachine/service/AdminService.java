package termProject.vendingmachine.service;

import java.io.IOException;

import static termProject.vendingmachine.VendingMachineThread.reader;
import static termProject.vendingmachine.domain.VendingMachine.DRINK_LIST;
import static termProject.vendingmachine.domain.VendingMachine.changeMoney;
import static termProject.vendingmachine.util.Util.DRINK;

public class AdminService {

    public static void changePrice(String[] clicked) {
        for (int i = 0; i < DRINK.size(); i++) {
            if (clicked[1].equals(DRINK.get(i))) {
                DRINK_LIST.get(i).updatePrice(Integer.parseInt(clicked[2]));
            }
        }
    }

    public static void readChangeMoney() throws IOException {
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            changeMoney.set(i, Integer.parseInt(str));
        }
    }
}
