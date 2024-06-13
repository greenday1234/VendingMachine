package termProject.vendingmachine.util;

import static termProject.vendingmachine.VendingMachineThread.password;
import static termProject.vendingmachine.VendingMachineThread.writer;
import static termProject.vendingmachine.domain.VendingMachine.*;
import static termProject.vendingmachine.util.Util.DRINK;
import static termProject.vendingmachine.util.Util.updateDate;

public class Write {

    public static void writePrice() {
        for (int i = 0; i < DRINK.size(); i++) {
            writer.println(DRINK_LIST.get(i).getPrice());
        }
    }

    public static void writePassword() {
        writer.println(password.getPassword());
    }

    public static void writeChangeMoney() {
        for (Integer integer : changeMoney) {
            writer.println(integer);
        }
    }

    public static void writeQuantityList() {
        for (Integer integer : quantityList) {
            writer.println(integer);
        }
    }

    public static void writeStock(int index) {
        if (quantityList.get(index) != 0) {
            writer.println("- -");
        } else {
            writer.println(index + " " + updateDate());
        }
    }

    public static void writeStockDate() {
        for (int i = 0; i < 6; i++) {
            if (quantityList.get(i) == 0) {
                writer.println(updateDate());
            } else {
                writer.println("0");
            }
        }
    }
}
