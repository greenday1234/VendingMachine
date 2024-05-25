package termProject.vendingmachine.util;

import static termProject.vendingmachine.VendingMachineThread.password;
import static termProject.vendingmachine.VendingMachineThread.writer;
import static termProject.vendingmachine.domain.VendingMachine.changeMoney;
import static termProject.vendingmachine.domain.VendingMachine.quantityList;
import static termProject.vendingmachine.util.Util.updateDate;

public class Write {

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
