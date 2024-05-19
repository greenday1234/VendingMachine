package termProject.vendingmachine;

import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.util.Calculator;

import java.io.*;
import java.net.Socket;

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
                            writer.println(Calculator.payMoneyCal() - vendingMachine.getWater().getPrice());    // 입력 금액 변경 및 반환
                            Calculator.ChangePayMoneyCal(vendingMachine.getWater().getPrice());   // 거스름돈에 입금 금액 추가
                            break;
                        case "coffee":
                            vendingMachine.getCoffee().sellCoffee();  // 재고 수량 변경
                            vendingMachine.updateQuantityList(1);
                            writeQuantityList();    // 재고 수량 반환
                            writer.println(Calculator.payMoneyCal() - vendingMachine.getCoffee().getPrice());    // 입력 금액 변경 및 반환
                            Calculator.ChangePayMoneyCal(vendingMachine.getCoffee().getPrice());   // 거스름돈에 입금 금액 추가
                            break;
                        case "sportsDrink":
                            vendingMachine.getSportsDrink().sellSportsDrink();  // 재고 수량 변경
                            vendingMachine.updateQuantityList(2);
                            writeQuantityList();    // 재고 수량 반환
                            writer.println(Calculator.payMoneyCal() - vendingMachine.getSportsDrink().getPrice());    // 입력 금액 변경 및 반환
                            Calculator.ChangePayMoneyCal(vendingMachine.getSportsDrink().getPrice());   // 거스름돈에 입금 금액 추가
                            break;
                        case "highQualityCoffee":
                            vendingMachine.getHighQualityCoffee().sellHighQualityCoffee();  // 재고 수량 변경
                            vendingMachine.updateQuantityList(3);
                            writeQuantityList();    // 재고 수량 반환
                            writer.println(Calculator.payMoneyCal() - vendingMachine.getHighQualityCoffee().getPrice());    // 입력 금액 변경 및 반환
                            Calculator.ChangePayMoneyCal(vendingMachine.getHighQualityCoffee().getPrice());   // 거스름돈에 입금 금액 추가
                            break;
                        case "soda":
                            vendingMachine.getSoda().sellSoda();  // 재고 수량 변경
                            vendingMachine.updateQuantityList(4);
                            writeQuantityList();    // 재고 수량 반환
                            writer.println(Calculator.payMoneyCal() - vendingMachine.getSoda().getPrice());    // 입력 금액 변경 및 반환
                            Calculator.ChangePayMoneyCal(vendingMachine.getSoda().getPrice());   // 거스름돈에 입금 금액 추가
                            break;
                        case "specialDrink":
                            vendingMachine.getSpecialDrink().sellSpecialDrink();  // 재고 수량 변경
                            vendingMachine.updateQuantityList(5);
                            writeQuantityList();    // 재고 수량 반환
                            writer.println(Calculator.payMoneyCal() - vendingMachine.getSpecialDrink().getPrice());    // 입력 금액 변경 및 반환
                            Calculator.ChangePayMoneyCal(vendingMachine.getSpecialDrink().getPrice());   // 거스름돈에 입금 금액 추가
                            break;
                    }
                } else if (split[0].equals("money")){    // 화폐 입금
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
                } else if (split[0].equals("return")) { // 미구현---------------
                    switch (split[1]) {
                        case "":
                            Calculator.returnChange();   // 거스름돈 수량 변경
                            writer.println(remainMoney);    // 거스름돈 수량 반환
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