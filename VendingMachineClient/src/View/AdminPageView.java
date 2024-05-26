package View;

import message.MessageTexts;
import socket.SocketDto;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.Map;

import static View.VendingMachineView.*;
import static View.VendingMachineView.drink;
import static View.label.LabelService.*;

public class AdminPageView {

    private PrintWriter writer;

    private JLabel money10QuantityLabel;
    private JLabel money50QuantityLabel;
    private JLabel money100QuantityLabel;
    private JLabel money500QuantityLabel;
    private JLabel money1000QuantityLabel;

    private JFrame adminPageFrame;

    public AdminPageView(SocketDto socketDto) {

        writer = socketDto.getWriter();

        // 프레임
        adminPageFrame = new JFrame("관리자 페이지");
        adminPageFrame.setBounds(560,200,800,600);
        adminPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPageFrame.getContentPane().setLayout(new GridBagLayout());
        adminPageFrame.getContentPane().setBackground(new Color(255, 255, 255, 255));
        adminPageFrame.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3); // 여백 설정

        addBackButton(gbc, 0);

        money10QuantityLabel = addMoneyQuantityLabel(gbc, 0, 1, "10");
        money50QuantityLabel = addMoneyQuantityLabel(gbc, 1, 1, "50");
        money100QuantityLabel = addMoneyQuantityLabel(gbc, 2, 1, "100");
        money500QuantityLabel = addMoneyQuantityLabel(gbc, 3, 1, "500");
        money1000QuantityLabel = addMoneyQuantityLabel(gbc, 4, 1, "1000");

        addChangeDrinkNamePriceRow(gbc, 2, waterNameLabel, DRINK_LIST.get(0).getDrinkPrice(), 0);
        addStockTribe(gbc, 3, 0);
        addChangeDrinkNamePriceRow(gbc, 4, coffeeNameLabel, DRINK_LIST.get(1).getDrinkPrice(), 1);
        addStockTribe(gbc, 5, 1);
        addChangeDrinkNamePriceRow(gbc, 6, sportsDrinkNameLabel, DRINK_LIST.get(2).getDrinkPrice(), 2);
        addStockTribe(gbc, 7, 2);
        addChangeDrinkNamePriceRow(gbc, 8, highQualityCoffeeNameLabel, DRINK_LIST.get(3).getDrinkPrice(), 3);
        addStockTribe(gbc, 9, 3);
        addChangeDrinkNamePriceRow(gbc, 10, sodaNameLabel, DRINK_LIST.get(4).getDrinkPrice(), 4);
        addStockTribe(gbc, 11, 4);
        addChangeDrinkNamePriceRow(gbc, 12, specialDrinkNameLabel, DRINK_LIST.get(5).getDrinkPrice(), 5);
        addStockTribe(gbc, 13, 5);

        addDailySales(gbc, 14);
        addMonthlySales(gbc, 15);

        addCollectMoney(gbc, 16, "collectMoney");

        addPWChangeButton(gbc, 16, socketDto);

        adminPageFrame.setVisible(true);
    }

    private void addBackButton(GridBagConstraints gbc, int row) {
        JButton button = new JButton("창 닫기");
        button.setPreferredSize(new Dimension(50, 40));
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        button.addActionListener(e -> {
            adminPageFrame.setVisible(false);
            vendingMachineFrame.setVisible(true);
        });
        adminPageFrame.add(button, gbc);
    }

    private void addPWChangeButton(GridBagConstraints gbc, int row, SocketDto socketDto) {
        JButton button = new JButton("관리자 비밀번호 변경");
        gbc.gridx = 2;
        gbc.gridy = row;
        button.addActionListener(e -> {
            AdminPWChangeView.adminPWChangeView(socketDto);
        });
        adminPageFrame.add(button, gbc);
    }

    private void addStockTribe(GridBagConstraints gbc, int row, int index) {
        JLabel stockLabel = new JLabel("재고");
        Font font = stockLabel.getFont();
        stockLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        stockLabel.setPreferredSize(new Dimension(50, 50));
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(stockLabel, gbc);

        JLabel stockCheckLabel;
        if (stockDate.get(index).equals("0")) {
            stockCheckLabel = new JLabel(quantityList.get(index) + " 개");
        } else {
            stockCheckLabel = new JLabel(stockDate.get(index) + "  소진");
        }
        Font checkFont = stockCheckLabel.getFont();
        stockCheckLabel.setFont(checkFont.deriveFont(Font.PLAIN, 15));
        stockCheckLabel.setPreferredSize(new Dimension(50, 50));
        gbc.gridx = 1;
        gbc.gridy = row;
        adminPageFrame.add(stockCheckLabel, gbc);
    }

    private void addMonthlySales(GridBagConstraints gbc, int row) {
        JLabel monthlySalesLabel = new JLabel("월별 매출");
        Font font = monthlySalesLabel.getFont();
        monthlySalesLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        monthlySalesLabel.setPreferredSize(new Dimension(70, 50));
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(monthlySalesLabel, gbc);

        int index = 1;
        for (Map.Entry<String, Integer> entry : monthlySales.entrySet()) {
            addLabel(gbc, row, index++, entry.getKey(), entry.getValue());
        }
    }

    private void addDailySales(GridBagConstraints gbc, int row) {
        JLabel daySalesLabel = new JLabel("일별 매출");
        Font font = daySalesLabel.getFont();
        daySalesLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        daySalesLabel.setPreferredSize(new Dimension(70, 50));
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(daySalesLabel, gbc);

        int index = 1;
        for (Map.Entry<String, Integer> entry : dailySales.entrySet()) {
            addLabel(gbc, row, index++, entry.getKey(), entry.getValue());
        }
    }

    private void addLabel(GridBagConstraints gbc, int row, int index, String key, Integer value) {
        JLabel daySalesLabel = new JLabel(key + " " + value);
        Font font = daySalesLabel.getFont();
        daySalesLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        daySalesLabel.setPreferredSize(new Dimension(70, 50));
        gbc.gridx = index;
        gbc.gridy = row;
        adminPageFrame.add(daySalesLabel, gbc);
    }

    private void addCollectMoney(GridBagConstraints gbc, int row, String text) {
        JLabel moneyLabel = new JLabel("수금하기");
        Font font = moneyLabel.getFont();
        moneyLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        moneyLabel.setPreferredSize(new Dimension(70, 50));
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(moneyLabel, gbc);

        ImageIcon icon = new ImageIcon("image/" + text + ".png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        JButton button = new JButton(icon);
        gbc.gridx = 1;
        button.addActionListener(e -> {
            int collectMoney = checkCollectMoney();
            writeChangeMoney();
            updateChangeMoneyLabel();
            JOptionPane.showMessageDialog(adminPageFrame, collectMoney + MessageTexts.COLLECTMONEY.getText());
        });
        adminPageFrame.add(button, gbc);
    }

    private void updateChangeMoneyLabel() {
        money10QuantityLabel.setText("10 원 " + changeMoney.get(0) + " 개");
        money50QuantityLabel.setText("50 원 " + changeMoney.get(1) + " 개");
        money100QuantityLabel.setText("100 원 " + changeMoney.get(2) + " 개");
        money500QuantityLabel.setText("500 원 " + changeMoney.get(3) + " 개");
        money1000QuantityLabel.setText("1000 원 " + changeMoney.get(4) + " 개");
    }

    private void writeChangeMoney() {
        writer.println("collectMoney ");
        for (int i = 0; i < 5; i++) {
            writer.println(changeMoney.get(i));
        }
    }

    private int checkCollectMoney() {
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

    private void addChangeDrinkNamePriceRow(GridBagConstraints gbc, int row, JLabel drinkNameLabel, int price, int index) {
        JLabel nameLabel = drinkNameLabel;
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(nameLabel, gbc);

        JTextField nameTextField = new JTextField(drinkNameLabel.getText());
        gbc.gridx = 1;
        adminPageFrame.add(nameTextField, gbc);

        JLabel priceLabel = new JLabel(price + " 원");
        gbc.gridx = 2;
        adminPageFrame.add(priceLabel, gbc);

        JTextField priceTextField = new JTextField(Integer.toString(price));
        gbc.gridx = 3;
        adminPageFrame.add(priceTextField, gbc);

        JButton button = new JButton("변경");
        gbc.gridx = 4;
        button.addActionListener(e -> {
            checkIndex(index, nameLabel, priceLabel, nameTextField, priceTextField);
        });
        adminPageFrame.add(button, gbc);
    }

    private void checkIndex(int index, JLabel nameLabel, JLabel priceLabel, JTextField nameTextField, JTextField priceTextField) {
        switch (index) {
            case 0:
                DRINK_LIST.get(index).setDrinkName(nameTextField.getText());    // 이름 변경
                nameLabel.setText(DRINK_LIST.get(index).getDrinkName());
                DRINK_LIST.get(index).setDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price water " + DRINK_LIST.get(index).getDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(DRINK_LIST.get(index).getDrinkPrice() + " 원");
                break;
            case 1:
                DRINK_LIST.get(index).setDrinkName(nameTextField.getText());    // 이름 변경
                nameLabel.setText(DRINK_LIST.get(index).getDrinkName());
                DRINK_LIST.get(index).setDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price coffee " + DRINK_LIST.get(index).getDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(DRINK_LIST.get(index).getDrinkPrice() + " 원");
                break;
            case 2:
                DRINK_LIST.get(index).setDrinkName(nameTextField.getText());    // 이름 변경
                nameLabel.setText(DRINK_LIST.get(index).getDrinkName());
                DRINK_LIST.get(index).setDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price sportsDrink " + DRINK_LIST.get(index).getDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(DRINK_LIST.get(index).getDrinkPrice() + " 원");
                break;
            case 3:
                DRINK_LIST.get(index).setDrinkName(nameTextField.getText());    // 이름 변경
                nameLabel.setText(DRINK_LIST.get(index).getDrinkName());
                DRINK_LIST.get(index).setDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price highQualityCoffee " + DRINK_LIST.get(index).getDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(DRINK_LIST.get(index).getDrinkPrice() + " 원");
                break;
            case 4:
                DRINK_LIST.get(index).setDrinkName(nameTextField.getText());    // 이름 변경
                nameLabel.setText(DRINK_LIST.get(index).getDrinkName());
                DRINK_LIST.get(index).setDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price soda " + DRINK_LIST.get(index).getDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(DRINK_LIST.get(index).getDrinkPrice() + " 원");
                break;
            case 5:
                DRINK_LIST.get(index).setDrinkName(nameTextField.getText());    // 이름 변경
                nameLabel.setText(DRINK_LIST.get(index).getDrinkName());
                DRINK_LIST.get(index).setDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price specialDrink " + DRINK_LIST.get(index).getDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(DRINK_LIST.get(index).getDrinkPrice() + " 원");
                break;
        }
    }

    private JLabel addMoneyQuantityLabel(GridBagConstraints gbc, int index, int row, String text) {
        JLabel label = new JLabel(text + " 원 " + changeMoney.get(index) + " 개", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 15));
        label.setPreferredSize(new Dimension(130, 50));
        gbc.gridx = index;
        gbc.gridy = row;
        adminPageFrame.add(label, gbc);
        return label;
    }

    public static void adminPageView(SocketDto socketDto) {
        new AdminPageView(socketDto);
    }
}
