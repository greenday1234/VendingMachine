package View.label;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static View.VendingMachineView.*;

public class LabelService {

    public static java.util.List<JLabel> DRINK_NAME_LABEL = new ArrayList<>();
    public static java.util.List<JLabel> DRINK_QUANTITY_LABEL = new ArrayList<>();
    public static List<JLabel> DRINK_PRICE_LABEL = new ArrayList<>();

    public static JLabel waterPriceLabel;
    public static JLabel coffeePriceLabel;
    public static JLabel sportsDrinkPriceLabel;
    public static JLabel highQualityCoffeePriceLabel;
    public static JLabel sodaPriceLabel;
    public static JLabel specialDrinkPriceLabel;

    public static JLabel waterQuantityLabel;
    public static JLabel coffeeQuantityLabel;
    public static JLabel sportsDrinkQuantityLabel;
    public static JLabel highQualityCoffeeQuantityLabel;
    public static JLabel sodaQuantityLabel;
    public static JLabel specialDrinkQuantityLabel;

    public static JLabel payMoneyLabel;

    public static JLabel waterNameLabel;
    public static JLabel coffeeNameLabel;
    public static JLabel sportsDrinkNameLabel;
    public static JLabel highQualityCoffeeNameLabel;
    public static JLabel sodaNameLabel;
    public static JLabel specialDrinkNameLabel;

    public void drinkQuantityInit() {
        DRINK_QUANTITY_LABEL.add(waterQuantityLabel);
        DRINK_QUANTITY_LABEL.add(coffeeQuantityLabel);
        DRINK_QUANTITY_LABEL.add(sportsDrinkQuantityLabel);
        DRINK_QUANTITY_LABEL.add(highQualityCoffeeQuantityLabel);
        DRINK_QUANTITY_LABEL.add(sodaQuantityLabel);
        DRINK_QUANTITY_LABEL.add(specialDrinkQuantityLabel);
    }

    public void drinkPriceInit() {
        DRINK_PRICE_LABEL.add(waterPriceLabel);
        DRINK_PRICE_LABEL.add(coffeePriceLabel);
        DRINK_PRICE_LABEL.add(sportsDrinkPriceLabel);
        DRINK_PRICE_LABEL.add(highQualityCoffeePriceLabel);
        DRINK_PRICE_LABEL.add(sodaPriceLabel);
        DRINK_PRICE_LABEL.add(specialDrinkPriceLabel);
    }

    public void drinkNameLabelInit() {
        DRINK_NAME_LABEL.add(waterNameLabel);
        DRINK_NAME_LABEL.add(coffeeNameLabel);
        DRINK_NAME_LABEL.add(sportsDrinkNameLabel);
        DRINK_NAME_LABEL.add(highQualityCoffeeNameLabel);
        DRINK_NAME_LABEL.add(sodaNameLabel);
        DRINK_NAME_LABEL.add(specialDrinkNameLabel);
    }

    public void addMoneyLabel() {
        payMoneyLabel = new JLabel(allPayMoney + " 원", SwingConstants.CENTER);
        Font font = payMoneyLabel.getFont();
        payMoneyLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        payMoneyLabel.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(payMoneyLabel);
    }

    public JLabel addNameLabel(JLabel nameLabel, String text) {
        nameLabel = new JLabel(text, SwingConstants.CENTER);
        Font font = nameLabel.getFont();
        nameLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        nameLabel.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(nameLabel);
        return nameLabel;
    }

    public JLabel addQuantityLabel(int index) {
        JLabel label = new JLabel(DRINK_LIST.get(index).getQuantity() + " 개", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    public JLabel addDrinkPriceLabel(int price) {
        JLabel label = new JLabel(price + " 원", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        label.setForeground(Color.RED); // 글자 색상 설정
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    /**
     * 변경해야 함!!!!
     */
    public void checkTextLabel(String text) {
        switch (text) {
            case "water":
                waterQuantityLabel.setText(quantityList.get(0) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, DRINK_LIST.get(0).getDrinkName() + " 을 구매하셨습니다.");
                break;
            case "coffee":
                coffeeQuantityLabel.setText(quantityList.get(1) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, DRINK_LIST.get(1).getDrinkName() + " 을 구매하셨습니다.");
                break;
            case "sportsDrink":
                sportsDrinkQuantityLabel.setText(quantityList.get(2) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, DRINK_LIST.get(2).getDrinkName() + " 을 구매하셨습니다.");
                break;
            case "highQualityCoffee":
                highQualityCoffeeQuantityLabel.setText(quantityList.get(3) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, DRINK_LIST.get(3).getDrinkName() + " 을 구매하셨습니다.");
                break;
            case "soda":
                sodaQuantityLabel.setText(quantityList.get(4) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, DRINK_LIST.get(4).getDrinkName() + " 을 구매하셨습니다.");
                break;
            case "specialDrink":
                specialDrinkQuantityLabel.setText(quantityList.get(5) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, DRINK_LIST.get(5).getDrinkName() + " 을 구매하셨습니다.");
                break;

        }
    }
}
