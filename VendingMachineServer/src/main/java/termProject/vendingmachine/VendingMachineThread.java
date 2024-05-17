package termProject.vendingmachine;

import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.util.Calculator;

import java.io.*;
import java.net.Socket;
import java.util.List;

import static termProject.vendingmachine.domain.VendingMachine.*;

public class VendingMachineThread implements Runnable {

    private Socket socket;
    private VendingMachine vendingMachine;
    private BufferedReader reader;
    private PrintWriter writer;

    public VendingMachineThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        vendingMachine = new VendingMachine();

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writePayMoney(writer);  // 초기 입력 금액 반환
            writeQuantityList(writer);  // 초기 재고 수량 반환
            writeChangeMoney(writer);   // 초기 거스름돈 수량 반환

            while (true) {
                String clicked = reader.readLine();
                String[] split = clicked.split(" ");
                if (split[0].equals("drink")) { // 음료 결제
                    switch (split[1]) {
                        case "water":
                            vendingMachine.getWater().sellWater();  // 재고 수량 변경
                            vendingMachine.updateQuantityList(0);
                            writeQuantityList();    // 재고 수량 반환

                            // 거스름돈 계산
                            Calculator.ChangePayMoneyCal(vendingMachine.getWater().getPrice());
                            // 거스름돈 수량 변경

                            // 거스름돈 수량 반환

                            // 입력 금액 변경

                            // 입력 금액 반환

                            break;
                        case "coffee":
                            break;
                        case "sportsDrink":
                            break;
                        case "highQualityCoffee":
                            break;
                        case "soda":
                            break;
                        case "specialDrink":
                            break;
                    }
                } else {    // 화폐 입금
                    switch (split[1]) {
                        case "10":
                            vendingMachine.updatePayMoney(0);
                            writer.println(vendingMachine.getPayMoneyResult()); // 총 금액 전송
                            writePayMoneyList();    // 입력 금액 리스트 전송
                            break;
                        case "50":
                            vendingMachine.updatePayMoney(1);
                            writer.println(vendingMachine.getPayMoneyResult()); // 총 금액 전송
                            writePayMoneyList();    // 입력 금액 리스트 전송
                            break;
                        case "100":
                            vendingMachine.updatePayMoney(2);
                            writer.println(vendingMachine.getPayMoneyResult()); // 총 금액 전송
                            writePayMoneyList();    // 입력 금액 리스트 전송
                            break;
                        case "500":
                            vendingMachine.updatePayMoney(3);
                            writer.println(vendingMachine.getPayMoneyResult()); // 총 금액 전송
                            writePayMoneyList();    // 입력 금액 리스트 전송
                            break;
                        case "1000":
                            vendingMachine.updatePayMoney(4);
                            writer.println(vendingMachine.getPayMoneyResult()); // 총 금액 전송
                            writePayMoneyList();    // 입력 금액 리스트 전송
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeQuantityList() {
        for (Integer integer : quantityList) {
            writer.println(integer);
        }
    }

    private void writeChangeMoney(PrintWriter writer) {
        for (Integer integer : changeMoney) {
            writer.println(integer);
        }
    }

    private void writePayMoneyList() {
        for (Integer integer : payMoney) {
            writer.println(integer);
        }
    }

    private static void writeQuantityList(PrintWriter writer) {
        for (Integer integer : quantityList) {
            writer.println(integer);
        }
    }

    private static void writePayMoney(PrintWriter writer) {
        for (Integer integer : payMoney) {
            writer.println(integer);
        }
    }
}