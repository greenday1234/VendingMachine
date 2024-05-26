package util;

import java.io.IOException;

import static View.VendingMachineView.*;

public class ReadService {

    public void readStockCheck() throws IOException {
        String stock = reader.readLine();
        String[] split = stock.split(" ");
        if (!split[0].equals("-")) {
            stockDate.set(Integer.parseInt(split[0]), split[1]);
        }
    }

    public void readMonthlySales() throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("done")) {
            String[] parts = line.split(" ");
            monthlySales.put(parts[0], Integer.parseInt(parts[1].trim()));
        }
    }

    public void readDailySales() throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("done")) {
            String[] parts = line.split(" ");
            dailySales.put(parts[0], Integer.parseInt(parts[1].trim()));
        }
    }

    public void readUpdateQuantity() throws IOException {
        for (int i = 0; i < 6; i++) {
            String quantity = reader.readLine();
            quantityList.set(i, Integer.parseInt(quantity));
        }
    }

    public void readChangeMoney() throws IOException {
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            changeMoney.set(i, Integer.parseInt(str));
        }
    }

    public String readRemainMoney() throws IOException {
        StringBuilder result = new StringBuilder();
        String str;
        for (int i = 0; i < 5; i++) {
            str = reader.readLine();
            if (str.equals("")){
                continue;
            }
            result.append("\n").append(str);
        }
        return result.toString();
    }
}
