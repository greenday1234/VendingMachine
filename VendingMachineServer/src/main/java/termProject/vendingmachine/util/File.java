package termProject.vendingmachine.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static termProject.vendingmachine.VendingMachineThread.*;
import static termProject.vendingmachine.util.Util.updateDate;

public class File {

    public static void addDailySale(int amount, int PORT) throws IOException {
        String date = updateDate();
        Integer price = dailySales.get(date);
        if (price == null) {
            price = 0;
        }
        int result = price + amount;
        dailySales.put(date, result);

        for (Map.Entry<String, Integer> entry : dailySales.entrySet()) {
            if (updateSale(entry.getKey(), result, "./sales" + "/" + PORT + "/dailySales.txt")) {
                dailyFileWriter.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        }
        dailyFileWriter.flush();
    }

    // 일별 매출 데이터를 기반으로 월별 매출 데이터를 생성하는 메소드
    public static void calculateMonthlySales(int PORT) throws IOException {
        monthlySales.clear();

        for (Map.Entry<String, Integer> entry : dailySales.entrySet()) {
            String date = entry.getKey();
            Integer sales = entry.getValue();
            updateMonthlySales(date, sales);
        }

        for (Map.Entry<String, Integer> entry : monthlySales.entrySet()) {
            if (updateSale(entry.getKey(), entry.getValue(), "./sales" + "/" + PORT + "/monthlySales.txt")) {
                monthlyFileWriter.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        }
        monthlyFileWriter.flush();
    }

    public static void readAndWriteDailyFile(int PORT) throws IOException {
        dailyReader.close();

        dailyReader = new BufferedReader(new FileReader("./sales" + "/" + PORT + "/dailySales.txt"));

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

    public static void readAndWriteMonthlyFile(int PORT) throws IOException {
        monthlyReader.close();

        monthlyReader = new BufferedReader(new FileReader("./sales" + "/" + PORT + "/monthlySales.txt"));

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

    // 월별 매출 데이터를 업데이트하는 메소드
    private static void updateMonthlySales(String date, int amount) {
        String month = date.substring(0, 7); // "YYYY-MM" 형식으로 월 추출
        monthlySales.put(month, monthlySales.getOrDefault(month, 0) + amount);
    }

    private static Boolean updateSale(String date, int result, String path) throws IOException {
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
}
