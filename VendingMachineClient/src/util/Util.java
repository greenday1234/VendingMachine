package util;

import domain.drink.*;
import message.ExceptionTexts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static View.VendingMachineView.*;
import static View.label.LabelService.*;

public class Util {

    public static final List<String> DRINK = new ArrayList<>(List.of("water", "coffee", "sportsDrink", "highQualityCoffee", "soda", "specialDrink"));
    public static List<Drink> DRINK_LIST = new ArrayList<>();
    public static final List<Integer> COIN_VALUES = new ArrayList<>(List.of(10, 50, 100, 500, 1000));
    public static final int[] prices = {450, 500, 550, 700, 750, 800};


    public static void initProcess() throws IOException {
        quantityListInit(); // 재고 수량 초기화
        changeMoneyInit();  // 거스름돈 수량 초기화
        stockDateInit();    // 재고 소진 일자 초기화
        passwordInit(); // 비밀번호 초기화
    }

    public static void drinkListInit() {
        DRINK_LIST.add(new Water("물", 450, quantityList.get(0)));
        DRINK_LIST.add(new Coffee("커피", 500, quantityList.get(1)));
        DRINK_LIST.add(new SportsDrink("이온음료", 550, quantityList.get(2)));
        DRINK_LIST.add(new HighQualityCoffee("고급 커피", 700, quantityList.get(3)));
        DRINK_LIST.add(new Soda("탄산음료", 750, quantityList.get(4)));
        DRINK_LIST.add(new SpecialDrink("특별음료", 800, quantityList.get(5)));
    }

    public static void passwordInit() throws IOException {
        String str = reader.readLine();
        password.updatePassword(str);
    }

    public static void changeMoneyInit() throws IOException {
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            changeMoney.add(Integer.parseInt(str));
        }
    }

    public static void stockDateInit() throws IOException {
        for (int i = 0; i < 6; i++) {
            String str = reader.readLine();
            stockDate.add(str);
        }
    }

    public static void quantityListInit() throws IOException {
        for (int i = 0; i < 6; i++) {
            String str = reader.readLine();
            quantityList.add(Integer.parseInt(str));
        }
    }

    public static void updateAddMoney() throws IOException {
        allPayMoney = Integer.parseInt(reader.readLine());
        payMoneyLabel.setText(allPayMoney + " 원");
    }

    public static void updateDeleteMoney() throws IOException {
        allPayMoney = Integer.parseInt(reader.readLine());
        payMoneyLabel.setText(allPayMoney + " 원");
    }

    public static void disConnectSocket() {
        try {
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(ExceptionTexts.SOCKET_DISCONNECTION_FAIL.getText(), e);
        }
    }

    public static void payMoneyCheck() {

        for (int i = 0; i < prices.length; i++) {
            if (allPayMoney >= prices[i]) {
                DRINK_PRICE_LABEL.get(i).setForeground(Color.GREEN);
            } else {
                DRINK_PRICE_LABEL.get(i).setForeground(Color.RED);
            }
        }
    }

    public static void check(int index, JLabel priceLabel, JTextField nameTextField, JTextField priceTextField) {
        for (int i = 0; i < DRINK.size(); i++) {
            if (index == i) {
                DRINK_LIST.get(i).setDrinkName(nameTextField.getText());    // 이름 변경
                ADMIN_DRINK_NAME_LABEL.get(i).setText(DRINK_LIST.get(i).getDrinkName());
                DRINK_LIST.get(i).setDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price " + DRINK.get(i) + " " + DRINK_LIST.get(i).getDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(DRINK_LIST.get(i).getDrinkPrice() + " 원");
            }
        }
    }

    public static ImageIcon getImageIcon(String text) {
        ImageIcon icon = new ImageIcon("image/" + text + ".png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        return icon;
    }

    public static int checkCollectMoney() {
        int result = 0;
        for (int i = 0; i < changeMoney.size(); i++) {
            Integer money = changeMoney.get(i);
            if (money > 5) {
                int excess = money - 5;
                result += COIN_VALUES.get(i) * excess;
                changeMoney.set(i, 5);
            }
        }

        return result;
    }

    public static void writeChangeMoney() {
        writer.println("collectMoney ");
        for (int i = 0; i < 5; i++) {
            writer.println(changeMoney.get(i));
        }
    }

    public static void updateChangeMoneyLabel() {
        for (int i = 0; i < 5; i++) {
            CHANGE_MONEY_LABEL.get(i).setText(COIN_VALUES.get(i) + " 원" + changeMoney.get(i) + " 개");
        }
    }
}
