package termProject.vendingmachine;

import lombok.extern.slf4j.Slf4j;
import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.login.Password;
import termProject.vendingmachine.message.ExceptionTexts;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static termProject.vendingmachine.config.Config.socketConfig;
import static termProject.vendingmachine.service.AdminService.*;
import static termProject.vendingmachine.service.DepositService.moneyDeposit;
import static termProject.vendingmachine.service.ReturnService.returnRemainMoney;
import static termProject.vendingmachine.service.SellService.sellDrink;
import static termProject.vendingmachine.util.Util.checkClicked;
import static termProject.vendingmachine.util.Write.*;

@Slf4j
public class VendingMachineThread implements Runnable {

    public ServerSocket serverSocket;
    public int PORT;
    public static Socket socket;
    public static BufferedReader reader;
    public static PrintWriter writer;
    public static BufferedReader dailyReader;
    public static BufferedReader monthlyReader;
    public static FileWriter dailyFileWriter;
    public static FileReader dailyFileReader;
    public static FileWriter monthlyFileWriter;
    public static FileReader monthlyFileReader;

    public static VendingMachine vendingMachine;
    public static Password password;

    // 일별 판매 금액 데이터 생성
    public static Map<String, Integer> dailySales = new HashMap<>();
    // 월별 판매 금액 데이터 생성
    public static Map<String, Integer> monthlySales = new HashMap<>();

    public VendingMachineThread(Socket socket, int PORT) throws IOException {
        this.socket = socket;
        this.PORT = PORT;
        vendingMachine = new VendingMachine();
        password = new Password("@1234567");
    }

    public void run() {
        try {
                socketConfig(PORT); // socket 을 사용해 파일 및 Reader, Writer 설정
                initWrite();    // 클라이언트에 전송할 초기 데이터

                while (true) {
                    String[] clicked = checkClicked();    // 클라이언트에서의 메시지 전송 확인
                    if (clicked[0].equals("drink")) {
                        sellDrink(clicked[1], PORT);   // 음료 결제
                    } else if (clicked[0].equals("money")) {
                        moneyDeposit(clicked[1]);    // 화폐 입금
                    } else if (clicked[0].equals("return")) { // 금액 반환
                        returnRemainMoney();
                    } else if (clicked[0].equals("price")) {
                        changePrice(clicked); // 가격 변경
                    } else if (clicked[0].equals("collectMoney")) {
                        readChangeMoney();  // 수금하기
                    } else if (clicked[0].equals("password")) {
                        password.updatePassword(clicked[1]);  // 비밀번호 변경
                    } else {
                        break;
                    }
                }
                socket.close();
        } catch (IOException e) {
            log.error(ExceptionTexts.EXCEPTION.getText(), e);
        }
    }

    private void initWrite() {
        writeQuantityList();  // 초기 재고 수량 반환
        writeChangeMoney();   // 초기 거스름돈 수량 반환
        writeStockDate();     // 초기 재고 소진 일자 반환
        writePassword();  // 비밀번호 반환
    }
}