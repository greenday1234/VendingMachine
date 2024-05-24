package termProject.vendingmachine;

import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.login.Password;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import static termProject.vendingmachine.domain.VendingMachine.*;

public class VendingMachineThread implements Runnable {

    private ServerSocket serverSocket;
    private int PORT;
    private Socket socket;
    private static VendingMachine vendingMachine;
    private Password password;
    private BufferedReader reader;
    private PrintWriter writer;
    private static BufferedReader dailyReader;
    private static BufferedReader monthlyReader;
    private static FileWriter dailyFileWriter;
    private static FileReader dailyFileReader;
    private static FileWriter monthlyFileWriter;
    private static FileReader monthlyFileReader;

    // 일별 판매 금액 데이터 생성
    private static Map<String, Integer> dailySales = new HashMap<>();
    // 월별 판매 금액 데이터 생성
    private static Map<String, Integer> monthlySales = new HashMap<>();

    public VendingMachineThread(ServerSocket serverSocket, int PORT) throws IOException {
        this.serverSocket = serverSocket;
        this.PORT = PORT;
        vendingMachine = new VendingMachine();
        password = new Password("@1234567");
    }

    public void run() {
        try {
            while (true) {
                this.socket = serverSocket.accept();
                System.out.println(PORT + "포트로 클라이언트가 연결되었습니다.");

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                dailyFileWriter = new FileWriter("./sales" + "/" + PORT + "/dailySales.txt");
                dailyFileReader = new FileReader("./sales" + "/" + PORT + "/dailySales.txt");
                monthlyFileWriter = new FileWriter("./sales" + "/" + PORT + "/monthlySales.txt");
                monthlyFileReader = new FileReader("./sales" + "/" +PORT + "/monthlySales.txt");
                dailyReader = new BufferedReader(dailyFileReader);
                monthlyReader = new BufferedReader(monthlyFileReader);

                writeQuantityList(writer);  // 초기 재고 수량 반환
                writeChangeMoney(writer);   // 초기 거스름돈 수량 반환
                writeStockDate(writer);     // 초기 재고 소진 일자 반환
                writePassword(writer);  // 비밀번호 반환

                while (true) {
                    String clicked = reader.readLine();
                    String[] split = clicked.split(" ");
                    if (split[0].equals("drink")) { // 음료 결제
                        switch (split[1]) {
                            case "water":
                                addDailySale(updateDate(), vendingMachine.getWater().getPrice());   // 전체 판매 금액 변경 일별
                                calculateMonthlySales();    // 전체 판매 금액 월별
                                vendingMachine.getWater().sellWater();  // 재고 수량 변경
                                quantityList.set(0, vendingMachine.getWater().getQuantity());
                                writeQuantityList();    // 재고 수량 반환
                                writeStock(writer, 0);   // 재고 소진 일자 반환
                                remainMoney -= vendingMachine.getWater().getPrice();
                                writer.println(remainMoney);    // 입력 금액 변경 및 반환
                                readAndWriteDailyFile();    // 일별 판매 금액 반환
                                readAndWriteMonthlyFile();  // 월별 판매 금액 반환
                                break;
                            case "coffee":
                                addDailySale(updateDate(), vendingMachine.getCoffee().getPrice());
                                calculateMonthlySales();
                                vendingMachine.getCoffee().sellCoffee();  // 재고 수량 변경
                                quantityList.set(1, vendingMachine.getCoffee().getQuantity());
                                writeQuantityList();    // 재고 수량 반환
                                writeStock(writer, 1);   // 재고 소진 일자 반환
                                remainMoney -= vendingMachine.getCoffee().getPrice();
                                writer.println(remainMoney);    // 입력 금액 변경 및 반환
                                readAndWriteDailyFile();    // 일별 판매 금액 반환
                                readAndWriteMonthlyFile();  // 월별 판매 금액 반환
                                break;
                            case "sportsDrink":
                                addDailySale(updateDate(), vendingMachine.getSportsDrink().getPrice());
                                calculateMonthlySales();
                                vendingMachine.getSportsDrink().sellSportsDrink();  // 재고 수량 변경
                                quantityList.set(2, vendingMachine.getSportsDrink().getQuantity());
                                writeQuantityList();    // 재고 수량 반환
                                writeStock(writer, 2);   // 재고 소진 일자 반환
                                remainMoney -= vendingMachine.getSportsDrink().getPrice();
                                writer.println(remainMoney);    // 입력 금액 변경 및 반환
                                readAndWriteDailyFile();    // 일별 판매 금액 반환
                                readAndWriteMonthlyFile();  // 월별 판매 금액 반환
                                break;
                            case "highQualityCoffee":
                                addDailySale(updateDate(), vendingMachine.getHighQualityCoffee().getPrice());
                                calculateMonthlySales();
                                vendingMachine.getHighQualityCoffee().sellHighQualityCoffee();  // 재고 수량 변경
                                quantityList.set(3, vendingMachine.getHighQualityCoffee().getQuantity());
                                writeQuantityList();    // 재고 수량 반환
                                writeStock(writer, 3);   // 재고 소진 일자 반환
                                remainMoney -= vendingMachine.getHighQualityCoffee().getPrice();
                                writer.println(remainMoney);    // 입력 금액 변경 및 반환
                                readAndWriteDailyFile();    // 일별 판매 금액 반환
                                readAndWriteMonthlyFile();  // 월별 판매 금액 반환
                                break;
                            case "soda":
                                addDailySale(updateDate(), vendingMachine.getSoda().getPrice());
                                calculateMonthlySales();
                                vendingMachine.getSoda().sellSoda();  // 재고 수량 변경
                                quantityList.set(4, vendingMachine.getSoda().getQuantity());
                                writeQuantityList();    // 재고 수량 반환
                                writeStock(writer, 4);   // 재고 소진 일자 반환
                                remainMoney -= vendingMachine.getSoda().getPrice();
                                writer.println(remainMoney);    // 입력 금액 변경 및 반환
                                readAndWriteDailyFile();    // 일별 판매 금액 반환
                                readAndWriteMonthlyFile();  // 월별 판매 금액 반환
                                break;
                            case "specialDrink":
                                addDailySale(updateDate(), vendingMachine.getSpecialDrink().getPrice());
                                calculateMonthlySales();
                                vendingMachine.getSpecialDrink().sellSpecialDrink();  // 재고 수량 변경
                                quantityList.set(5, vendingMachine.getSpecialDrink().getQuantity());
                                writeQuantityList();    // 재고 수량 반환
                                writeStock(writer, 5);   // 재고 소진 일자 반환
                                remainMoney -= vendingMachine.getSpecialDrink().getPrice();
                                writer.println(remainMoney);    // 입력 금액 변경 및 반환
                                readAndWriteDailyFile();    // 일별 판매 금액 반환
                                readAndWriteMonthlyFile();  // 월별 판매 금액 반환
                                break;
                        }
                    } else if (split[0].equals("money")) {    // 화폐 입금
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

                        writer.println(remainMoney);    // 거스름돈 금액 반환
                        returnChange();   // 거스름돈 수량 변경
                        writeChangeMoney(writer);

                    } else if (split[0].equals("price")) {  // 가격 변경
                        switch (split[1]) {
                            case "water":
                                vendingMachine.getWater().updateWaterPrice(Integer.parseInt(split[2]));
                                break;
                            case "coffee":
                                vendingMachine.getCoffee().updateCoffeePrice(Integer.parseInt(split[2]));
                                break;
                            case "sportsDrink":
                                vendingMachine.getSportsDrink().updateSportsDrinkPrice(Integer.parseInt(split[2]));
                                break;
                            case "highQualityCoffee":
                                vendingMachine.getHighQualityCoffee().updateHighQualityCoffeePrice(Integer.parseInt(split[2]));
                                break;
                            case "soda":
                                vendingMachine.getSoda().updateSodaPrice(Integer.parseInt(split[2]));
                                break;
                            case "specialDrink":
                                vendingMachine.getSpecialDrink().updateSpecialDrinkPrice(Integer.parseInt(split[2]));
                                break;
                        }
                    } else if (split[0].equals("collectMoney")) {   // 수금
                        readChangeMoney();
                    } else if (split[0].equals("password")) {   // 비밀번호 변경
                        password.updatePassword(split[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStock(PrintWriter writer, int index) {
        if (quantityList.get(index) != 0) {
            writer.println("- -");
        } else {
            writer.println(index + " " + updateDate());
        }
    }

    private void writeStockDate(PrintWriter writer) {
        for (int i = 0; i < 6; i++) {
            if (quantityList.get(i) == 0) {
                writer.println(updateDate());
            } else {
                writer.println("0");
            }
        }
    }

    private void readAndWriteMonthlyFile() throws IOException {
        monthlyReader.close();

        monthlyReader = new BufferedReader(new FileReader("./sales/monthlySales.txt"));

        String line;
        monthlyReader.mark(0);
        while ((line = monthlyReader.readLine()) != null) {
            String[] parts = line.split(":");
                String date = parts[0];
                int sales = Integer.parseInt(parts[1].trim());
                writer.println(date + " " + sales);
        }
        writer.println("done");
    }

    private void readAndWriteDailyFile() throws IOException {
        dailyReader.close();

        dailyReader = new BufferedReader(new FileReader("./sales/dailySales.txt"));

        String line;
        dailyReader.mark(0);
        while ((line = dailyReader.readLine()) != null) {
            String[] parts = line.split(":");
                String date = parts[0];
                int sales = Integer.parseInt(parts[1].trim());
                writer.println(date + " " + sales);
        }
        writer.println("done");
    }

    // 일별 매출 데이터를 기반으로 월별 매출 데이터를 생성하는 메소드
    public static void calculateMonthlySales() throws IOException {
        monthlySales.clear();

        for (Map.Entry<String, Integer> entry : dailySales.entrySet()) {
            String date = entry.getKey();
            Integer sales = entry.getValue();
            updateMonthlySales(date, sales);
        }

        for (Map.Entry<String, Integer> entry : monthlySales.entrySet()) {
            if (updateSale(entry.getKey(), entry.getValue(), "./sales/monthlySales.txt")) {
                monthlyFileWriter.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        }
        monthlyFileWriter.flush();
    }

    // 월별 매출 데이터를 업데이트하는 메소드
    public static void updateMonthlySales(String date, int amount) {
        String month = date.substring(0, 7); // "YYYY-MM" 형식으로 월 추출
        monthlySales.put(month, monthlySales.getOrDefault(month, 0) + amount);
    }

    private void readChangeMoney() throws IOException {
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            changeMoney.set(i, Integer.parseInt(str));
        }
    }

    public void returnChange() {
        for (int i = COIN_VALUES.size() -1; i >= 0; i--) {
            int coinValue = COIN_VALUES.get(i);
            int coinCount = Math.min(remainMoney / coinValue, changeMoney.get(i)); // 사용 가능한 동전 개수와 최소값 계산
            if (coinCount > 0) {
                remainMoney -= coinValue * coinCount;
                changeMoney.set(i, changeMoney.get(i) - coinCount); // 사용한 동전 개수만큼 감소
                writer.println(coinValue + "원 " + coinCount + "개");
            } else {
                writer.println("");
            }
        }
    }

    private static String updateDate() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(now);
    }

    private static void addDailySale(String date, int amount) throws IOException {
        Integer price = dailySales.get(date);
        if (price == null) {
            price = 0;
        }
        int result = price + amount;
        dailySales.put(date, result);

        for (Map.Entry<String, Integer> entry : dailySales.entrySet()) {
            if (updateSale(entry.getKey(), result, "./sales/dailySales.txt")) {
                dailyFileWriter.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        }
        dailyFileWriter.flush();
    }

    public static Boolean updateSale(String date, int result, String path) throws IOException {
        // 파일의 모든 내용을 읽음
        List<String> lines = Files.readAllLines(Paths.get(path));
        List<String> updatedLines = new ArrayList<>();
        Boolean tmp = true;
        // 파일의 각 줄을 처리
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2 && parts[0].equals(date)) {
                // 특정 날짜의 값을 변경
                updatedLines.add(parts[0] + ":" + result);
                tmp = false;
            } else {
                updatedLines.add(line);
            }
        }

        // 변경된 내용을 파일에 다시 씀
        Files.write(Paths.get(path), updatedLines);
        return tmp;
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