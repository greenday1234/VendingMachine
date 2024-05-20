package termProject.vendingmachine;

import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.login.Password;

import java.io.*;
import java.net.Socket;

import static termProject.vendingmachine.domain.VendingMachine.*;

public class VendingMachineThread implements Runnable {

    private Socket socket;
    private VendingMachine vendingMachine;
    private Password password;
    private BufferedReader reader;
    private PrintWriter writer;

    public VendingMachineThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        vendingMachine = new VendingMachine();
        password = new Password("@1234567");

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writeQuantityList(writer);  // 초기 재고 수량 반환
            writeChangeMoney(writer);   // 초기 거스름돈 수량 반환
            writePassword(writer);  // 비밀번호 반환

            while (true) {
                String clicked = reader.readLine();
                String[] split = clicked.split(" ");
                if (split[0].equals("drink")) { // 음료 결제
                    switch (split[1]) {
                        case "water":
                            vendingMachine.getWater().sellWater();  // 재고 수량 변경
                            quantityList.set(0, vendingMachine.getWater().getQuantity());
                            writeQuantityList();    // 재고 수량 반환
                            remainMoney -= vendingMachine.getWater().getPrice();
                            writer.println(remainMoney);    // 입력 금액 변경 및 반환
                            break;
                        case "coffee":
                            vendingMachine.getCoffee().sellCoffee();  // 재고 수량 변경
                            quantityList.set(1, vendingMachine.getCoffee().getQuantity());
                            writeQuantityList();    // 재고 수량 반환
                            remainMoney -= vendingMachine.getCoffee().getPrice();
                            writer.println(remainMoney);    // 입력 금액 변경 및 반환
                            break;
                        case "sportsDrink":
                            vendingMachine.getSportsDrink().sellSportsDrink();  // 재고 수량 변경
                            quantityList.set(2, vendingMachine.getSportsDrink().getQuantity());
                            writeQuantityList();    // 재고 수량 반환
                            remainMoney -= vendingMachine.getSportsDrink().getPrice();
                            writer.println(remainMoney);    // 입력 금액 변경 및 반환
                            break;
                        case "highQualityCoffee":
                            vendingMachine.getHighQualityCoffee().sellHighQualityCoffee();  // 재고 수량 변경
                            quantityList.set(3, vendingMachine.getHighQualityCoffee().getQuantity());
                            writeQuantityList();    // 재고 수량 반환
                            remainMoney -= vendingMachine.getHighQualityCoffee().getPrice();
                            writer.println(remainMoney);    // 입력 금액 변경 및 반환
                            break;
                        case "soda":
                            vendingMachine.getSoda().sellSoda();  // 재고 수량 변경
                            quantityList.set(4, vendingMachine.getSoda().getQuantity());
                            writeQuantityList();    // 재고 수량 반환
                            remainMoney -= vendingMachine.getSoda().getPrice();
                            writer.println(remainMoney);    // 입력 금액 변경 및 반환
                            break;
                        case "specialDrink":
                            vendingMachine.getSpecialDrink().sellSpecialDrink();  // 재고 수량 변경
                            quantityList.set(5, vendingMachine.getSpecialDrink().getQuantity());
                            writeQuantityList();    // 재고 수량 반환
                            remainMoney -= vendingMachine.getSpecialDrink().getPrice();
                            writer.println(remainMoney);    // 입력 금액 변경 및 반환
                            break;
                    }
                } else if (split[0].equals("money")){    // 화폐 입금
                    switch (split[1]) {
                        case "10":
                            vendingMachine.updateChangeMoney(0);
                            remainMoney += 10;
                            writer.println(remainMoney);   // 총 금액 전송
                            break;
                        case "50":
                            vendingMachine.updateChangeMoney(1);
                            remainMoney += 50;
                            writer.println(remainMoney);   // 총 금액 전송
                            break;
                        case "100":
                            vendingMachine.updateChangeMoney(2);
                            remainMoney += 100;
                            writer.println(remainMoney);   // 총 금액 전송
                            break;
                        case "500":
                            vendingMachine.updateChangeMoney(3);
                            remainMoney += 500;
                            writer.println(remainMoney);   // 총 금액 전송
                            break;
                        case "1000":
                            vendingMachine.updateChangeMoney(4);
                            remainMoney += 1000;
                            writer.println(remainMoney);   // 총 금액 전송
                            break;
                    }
                } else if (split[0].equals("return")) { // 금액 반환
                    switch (split[1]) {
                        case "payBack":
                            writer.println(remainMoney);    // 거스름돈 금액 반환
                            returnChange();   // 거스름돈 수량 변경
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnChange() {
        for (int i = COIN_VALUES.size() -1; i >= 0; i--) {
            int coinValue = COIN_VALUES.get(i);
            int coinCount = Math.min(remainMoney / coinValue, changeMoney.get(i)); // 사용 가능한 동전 개수와 최소값 계산
            if (coinCount > 0) {
                System.out.println(coinValue + "원 동전: " + coinCount + "개");
                remainMoney -= coinValue * coinCount;
                changeMoney.set(i, changeMoney.get(i) - coinCount); // 사용한 동전 개수만큼 감소
                writer.println(coinValue + "원 " + coinCount + "개");
            } else {
                writer.println("");
            }
        }
    }

    private void writePassword(PrintWriter writer) {
        writer.println(password.getPassword());
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

    private static void writeQuantityList(PrintWriter writer) {
        for (Integer integer : quantityList) {
            writer.println(integer);
        }
    }
}