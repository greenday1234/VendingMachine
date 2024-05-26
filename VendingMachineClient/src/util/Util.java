package util;

import domain.drink.*;
import message.ExceptionTexts;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static View.VendingMachineView.*;
import static View.label.LabelService.*;

public class Util {

    public static final List<String> DRINK = new ArrayList<>(List.of("water", "coffee", "sportsDrink", "highQualityCoffee", "soda", "specialDrink"));

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
        if (allPayMoney >= 450) {
            waterPriceLabel.setForeground(Color.GREEN);
        } else {
            waterPriceLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 500) {
            coffeePriceLabel.setForeground(Color.GREEN);
        } else {
            coffeePriceLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 550 ) {
            sportsDrinkPriceLabel.setForeground(Color.GREEN);
        } else {
            sportsDrinkPriceLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 700) {
            highQualityCoffeePriceLabel.setForeground(Color.GREEN);
        } else {
            highQualityCoffeePriceLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 750) {
            sodaPriceLabel.setForeground(Color.GREEN);
        } else {
            sodaPriceLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 800) {
            specialDrinkPriceLabel.setForeground(Color.GREEN);
        } else {
            specialDrinkPriceLabel.setForeground(Color.RED);
        }
    }
}
