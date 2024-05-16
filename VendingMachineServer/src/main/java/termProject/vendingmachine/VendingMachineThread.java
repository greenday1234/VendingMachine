package termProject.vendingmachine;

import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.validate.Validation;

import java.io.*;
import java.net.Socket;

public class VendingMachineThread implements Runnable {
    private Socket socket;

    public VendingMachineThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        Validation validation = new Validation();
        VendingMachine vendingMachine = new VendingMachine();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                String clicked = reader.readLine();
                switch (clicked) {
                    case "10":
                        vendingMachine.updatePayMoney(0);
                        writer.println(vendingMachine.getPayMoneyResult());
                        break;
                    case "50":
                        vendingMachine.updatePayMoney(1);
                        writer.println(vendingMachine.getPayMoneyResult());
                        break;
                    case "100":
                        vendingMachine.updatePayMoney(2);
                        writer.println(vendingMachine.getPayMoneyResult());
                        break;
                    case "500":
                        vendingMachine.updatePayMoney(3);
                        writer.println(vendingMachine.getPayMoneyResult());
                        break;
                    case "1000":
                        vendingMachine.updatePayMoney(4);
                        writer.println(vendingMachine.getPayMoneyResult());
                        break;
                }

                /*while (true) {
                    String buttonClicked = reader.readLine(); // 클라이언트로부터 버튼 클릭 이벤트 수신
                    System.out.println("클라이언트가 " + buttonClicked + " 버튼을 눌렀습니다.");

                    // 클라이언트에게 데이터 전송
                    writer.println("물");
                    writer.println("커피");
                    writer.println("스포츠 음료");
                    writer.println("고급 커피");
                    writer.println("소다");
                    writer.println("특별 음료");
                    break;
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}