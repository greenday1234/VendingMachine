package View;

import socket.SocketDto;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;

import static View.VendingMachineView.*;
import static View.VendingMachineView.drink;

public class AdminPageView {

    private BufferedReader reader;
    private PrintWriter writer;

    private final JLabel money10QuantityLabel;
    private final JLabel money50QuantityLabel;
    private final JLabel money100QuantityLabel;
    private final JLabel money500QuantityLabel;
    private final JLabel money1000QuantityLabel;

    private JFrame adminPageFrame;

    public AdminPageView(SocketDto socketDto) {

        reader = socketDto.getReader();
        writer = socketDto.getWriter();

        // 프레임
        adminPageFrame = new JFrame("관리자 페이지");
        adminPageFrame.setBounds(560,200,800,500);
        adminPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPageFrame.getContentPane().setLayout(new GridBagLayout());
        adminPageFrame.getContentPane().setBackground(new Color(255, 255, 255, 255));
        adminPageFrame.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3); // 여백 설정

        /**
         * 4. 수금메뉴 (반환을 위한 최소한의 화폐는 임의로 지정해 남김)
         * 5. 일별/월별 매출
         * 6. 재고 소진 날짜
         * 7. 비밀번호 설정
         */
        money10QuantityLabel = addMoneyQuantityLabel(gbc, 0, "10");
        money50QuantityLabel = addMoneyQuantityLabel(gbc, 1, "50");
        money100QuantityLabel = addMoneyQuantityLabel(gbc, 2, "100");
        money500QuantityLabel = addMoneyQuantityLabel(gbc, 3, "500");
        money1000QuantityLabel = addMoneyQuantityLabel(gbc, 4, "1000");

        addChangeDrinkNamePriceRow(gbc, 1, waterNameLabel, drink.getWaterPrice(), 0);
        addChangeDrinkNamePriceRow(gbc, 2, coffeeNameLabel, drink.getCoffeePrice(), 1);
        addChangeDrinkNamePriceRow(gbc, 3, sportsDrinkNameLabel, drink.getSportsDrinkPrice(), 2);
        addChangeDrinkNamePriceRow(gbc, 4, highQualityCoffeeNameLabel, drink.getHighQualityCoffeePrice(), 3);
        addChangeDrinkNamePriceRow(gbc, 5, sodaNameLabel, drink.getSodaPrice(), 4);
        addChangeDrinkNamePriceRow(gbc, 6, specialDrinkNameLabel, drink.getSpecialDrinkPrice(), 5);

        // 수금메뉴
        addCollectMoney(gbc, 7);

        // 프레임 표시
        adminPageFrame.setVisible(true);



        adminPageFrame.setVisible(true);
    }

    private void addCollectMoney(GridBagConstraints gbc, int row) {
        
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

        JButton button = new JButton("Submit");
        gbc.gridx = 4;
        button.addActionListener(e -> {
            checkIndex(index, nameLabel, priceLabel, nameTextField, priceTextField);
        });
        adminPageFrame.add(button, gbc);
    }

    private void checkIndex(int index, JLabel nameLabel, JLabel priceLabel, JTextField nameTextField, JTextField priceTextField) {
        switch (index) {
            case 0:
                drink.setWater(nameTextField.getText());    // 이름 변경
                nameLabel.setText(drink.getWater());
                drink.setWaterPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price water " + drink.getWaterPrice());  // 가격 서버에 전송
                priceLabel.setText(drink.getWaterPrice() + " 원");
                break;
            case 1:
                drink.setCoffee(nameTextField.getText());    // 이름 변경
                nameLabel.setText(drink.getCoffee());
                drink.setCoffeePrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price coffee " + drink.getCoffeePrice());  // 가격 서버에 전송
                priceLabel.setText(drink.getCoffeePrice() + " 원");
                break;
            case 2:
                drink.setSportsDrink(nameTextField.getText());    // 이름 변경
                nameLabel.setText(drink.getSportsDrink());
                drink.setSportsDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price sportsDrink " + drink.getSportsDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(drink.getSportsDrinkPrice() + " 원");
                break;
            case 3:
                drink.setHighQualityCoffee(nameTextField.getText());    // 이름 변경
                nameLabel.setText(drink.getHighQualityCoffee());
                drink.setHighQualityCoffeePrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price highQualityCoffee " + drink.getHighQualityCoffeePrice());  // 가격 서버에 전송
                priceLabel.setText(drink.getHighQualityCoffeePrice() + " 원");
                break;
            case 4:
                drink.setSoda(nameTextField.getText());    // 이름 변경
                nameLabel.setText(drink.getSoda());
                drink.setSodaPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price soda " + drink.getSodaPrice());  // 가격 서버에 전송
                priceLabel.setText(drink.getSodaPrice() + " 원");
                break;
            case 5:
                drink.setSpecialDrink(nameTextField.getText());    // 이름 변경
                nameLabel.setText(drink.getSpecialDrink());
                drink.setSpecialDrinkPrice(Integer.parseInt(priceTextField.getText()));    // 가격 변경
                writer.println("price specialDrink " + drink.getSpecialDrinkPrice());  // 가격 서버에 전송
                priceLabel.setText(drink.getSpecialDrinkPrice() + " 원");
                break;
        }
    }

    private JLabel addMoneyQuantityLabel(GridBagConstraints gbc, int index, String text) {
        JLabel label = new JLabel(text + " 원 " + changeMoney.get(index) + " 개", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 15));
        label.setPreferredSize(new Dimension(130, 50));
        gbc.gridx = index;
        gbc.gridy = 0;
        adminPageFrame.add(label, gbc);
        return label;
    }

    public static void adminPageView(SocketDto socketDto) {
        new AdminPageView(socketDto);
    }
}
