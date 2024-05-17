package termProject.vendingmachine;

import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.util.Calculator;
import termProject.vendingmachine.validate.Validation;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class VendingMachineThread implements Runnable {

    private Socket socket;
    private VendingMachine vendingMachine;
    private BufferedReader reader;
    private PrintWriter writer;

    public VendingMachineThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        Validation validation = new Validation();
        vendingMachine = new VendingMachine();

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writePayMoney(vendingMachine, writer);  // 초기 입력 금액 반환
            writeQuantityList(vendingMachine, writer);  // 초기 재고 수량 반환
            writeChangeMoney(vendingMachine, writer);   // 초기 거스름돈 수량 반환

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
        List<Integer> quantityList = vendingMachine.getQuantityList();
        for (Integer integer : quantityList) {
            writer.println(integer);
        }
    }

    private void writeChangeMoney(VendingMachine vendingMachine, PrintWriter writer) {
        List<Integer> changeMoney = vendingMachine.getChangeMoney();
        for (Integer integer : changeMoney) {
            writer.println(integer);
        }
    }

    private void writePayMoneyList() {
        List<Integer> payMoney = vendingMachine.getPayMoney();
        for (Integer integer : payMoney) {
            writer.println(integer);
        }
    }

    private static void writeQuantityList(VendingMachine vendingMachine, PrintWriter writer) {
        List<Integer> quantityList = vendingMachine.getQuantityList();
        for (Integer integer : quantityList) {
            writer.println(integer);
        }
    }

    private static void writePayMoney(VendingMachine vendingMachine, PrintWriter writer) {
        List<Integer> payMoney = vendingMachine.getPayMoney();
        for (Integer integer : payMoney) {
            writer.println(integer);
        }
    }
}