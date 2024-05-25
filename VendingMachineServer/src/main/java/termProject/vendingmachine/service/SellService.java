package termProject.vendingmachine.service;

import termProject.vendingmachine.domain.drink.Drink;

import java.io.IOException;

import static termProject.vendingmachine.VendingMachineThread.writer;
import static termProject.vendingmachine.domain.VendingMachine.*;
import static termProject.vendingmachine.util.File.*;
import static termProject.vendingmachine.util.Util.DRINK;
import static termProject.vendingmachine.util.Write.writeQuantityList;
import static termProject.vendingmachine.util.Write.writeStock;

public class SellService {

    public static void sellDrink(String clicked, int PORT) throws IOException {
        for (int i = 0; i < DRINK.size(); i++) {
            if (clicked.equals(DRINK.get(i))) {
                Drink drink = DRINK_LIST.get(i);
                writeFileSales(drink, PORT);  // 파일에 판매 금액 기입

                writeQuantity(i, drink);    // 재고 수량 및 소진 일자

                writeRemainMoney(drink);    // 입력 금액 변경 및 반환

                writeSales(PORT);   // 판매 금액 반환
            }
        }
    }

    private static void writeSales(int PORT) throws IOException {
        readAndWriteDailyFile(PORT);    // 일별 판매 금액 반환
        readAndWriteMonthlyFile(PORT);  // 월별 판매 금액 반환
    }

    private static void writeRemainMoney(Drink drink) {
        remainMoney -= drink.getPrice();
        writer.println(remainMoney);
    }

    private static void writeQuantity(int i, Drink drink) {
        drink.sellDrink();  // 재고 수량 변경
        quantityList.set(i, drink.getQuantity());
        writeQuantityList();    // 재고 수량 반환
        writeStock(i);   // 재고 소진 일자 반환
    }

    private static void writeFileSales(Drink drink, int PORT) throws IOException {
        addDailySale(drink.getPrice(), PORT);   // 전체 판매 금액 변경 일별
        calculateMonthlySales(PORT);    // 전체 판매 금액 월별
    }
}
