package View;

import message.MessageTexts;
import socket.SocketDto;
import validate.Validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineView {

    private final BufferedReader reader;
    private final PrintWriter writer;
    private final Socket socket;

    public static int allPayMoney = 0;
    public static final List<Integer> quantityList = new ArrayList<>(); // 재고 수량
    public static final List<Integer> changeMoney = new ArrayList<>();    // 거스름돈 수량
    public static int check1000 = 0;
    public static final List<Integer> COIN_VALUES = new ArrayList<>(List.of(10, 50, 100, 500, 1000));

    private final JLabel waterLabel;
    private final JLabel coffeeLabel;
    private final JLabel sportsDrinkLabel;
    private final JLabel highQualityCoffeeLabel;
    private final JLabel sodaLabel;
    private final JLabel specialDrinkLabel;

    private final JLabel waterQuantityLabel;
    private final JLabel coffeeQuantityLabel;
    private final JLabel sportsDrinkQuantityLabel;
    private final JLabel highQualityCoffeeQuantityLabel;
    private final JLabel sodaQuantityLabel;
    private final JLabel specialDrinkQuantityLabel;
    private final JLabel payMoneyLabel;

    public static JFrame vendingMachineFrame;

    public VendingMachineView(SocketDto socketDto) throws IOException {

        socket = socketDto.getSocket();

        reader = socketDto.getReader();
        writer = socketDto.getWriter();

        quantityListInit(); // 재고 수량 초기화
        changeMoneyInit();  // 거스름돈 수량 초기화

        // 프레임
        vendingMachineFrame = new JFrame("자판기 프로그램");
        vendingMachineFrame.setBounds(560,200,790,500);
        vendingMachineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendingMachineFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        vendingMachineFrame.getContentPane().setBackground(new Color(151, 100, 100));
        vendingMachineFrame.setLocationRelativeTo(null);

        addDrinkButton("water");
        addDrinkButton("coffee");
        addDrinkButton("sportsDrink");
        addDrinkButton("highQualityCoffee");
        addDrinkButton("soda");
        addDrinkButton("specialDrink");

        waterQuantityLabel = addQuantityLabel();
        coffeeQuantityLabel = addQuantityLabel();
        sportsDrinkQuantityLabel = addQuantityLabel();
        highQualityCoffeeQuantityLabel = addQuantityLabel();
        sodaQuantityLabel = addQuantityLabel();
        specialDrinkQuantityLabel = addQuantityLabel();

        waterLabel = addDrinkLabel(MessageTexts.WATER.getText());
        coffeeLabel = addDrinkLabel(MessageTexts.COFFEE.getText());
        sportsDrinkLabel = addDrinkLabel(MessageTexts.SPORTSDRINK.getText());
        highQualityCoffeeLabel = addDrinkLabel(MessageTexts.HIGHQUALITYCOFFEE.getText());
        sodaLabel = addDrinkLabel(MessageTexts.SODA.getText());
        specialDrinkLabel = addDrinkLabel(MessageTexts.SPECIALDRINK.getText());

        addMoneyButton("10");
        addMoneyButton("50");
        addMoneyButton("100");
        addMoneyButton("500");
        addMoneyButton("1000");

        addPayBackButton("payBack");

        payMoneyLabel = addMoneyLabel();

        addAdminButton(socketDto);

        vendingMachineFrame.setVisible(true);
    }

    private void changeMoneyInit() throws IOException {
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            changeMoney.add(Integer.parseInt(str));
        }
    }

    private void quantityListInit() throws IOException {
        for (int i = 0; i < 6; i++) {
            String str = reader.readLine();
            quantityList.add(Integer.parseInt(str));
        }
    }

    private void addDrinkButton(String text) {
        ImageIcon icon = new ImageIcon("image/" + text + ".png");
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Validation.validMoney(text)) { // 금액 검증
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.MONEY_LACK.getText());
                    return;
                }

                if (!Validation.validQuantity(text)) { // 재고 검증
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.QUANTITY_LACK.getText());
                    return;
                }
                if (!Validation.validChangeMoney(text)) {   // 거스름돈 검증(거스름돈이 부족해 돈을 바꿔주지 못하는 경우) (테스트 해봐야함)
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.CHANGE_MONEY_LACK.getText());
                    return;
                }

                writer.println("drink " + text);   // 서버에 전송
                try {
                    readUpdateQuantity();   // 재고 수량 읽기
                    checkTextLabel(text);   // label 변경

                    updateDeleteMoney();// 넣은 금액 변경
                    payMoneyCheck();  // 구매 가능 음료 글자 색 변환
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private void readUpdateQuantity() throws IOException {
        for (int i = 0; i < 6; i++) {
            String quantity = reader.readLine();
            quantityList.set(i, Integer.parseInt(quantity));
        }
    }

    private void checkTextLabel(String text) {
        switch (text) {
            case "water":
                waterQuantityLabel.setText(quantityList.get(0) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.WATER_SELL.getText());
                break;
            case "coffee":
                coffeeQuantityLabel.setText(quantityList.get(1) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.COFFEE_SELL.getText());
                break;
            case "sportsDrink":
                sportsDrinkQuantityLabel.setText(quantityList.get(2) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.SPORTSDRINK_SELL.getText());
                break;
            case "highQualityCoffee":
                highQualityCoffeeQuantityLabel.setText(quantityList.get(3) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.HIGHQUALITYCOFFEE_SELL.getText());
                break;
            case "soda":
                sodaQuantityLabel.setText(quantityList.get(4) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.SODA_SELL.getText());
                break;
            case "specialDrink":
                specialDrinkQuantityLabel.setText(quantityList.get(5) + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.SPECIALDRINK_SELL.getText());
                break;

        }
    }

    private void updateAddMoney() throws IOException {
        allPayMoney = Integer.parseInt(reader.readLine());
        payMoneyLabel.setText(allPayMoney + " 원");
    }

    private void updateDeleteMoney() throws IOException {
        allPayMoney = Integer.parseInt(reader.readLine());
        payMoneyLabel.setText(allPayMoney + " 원");
    }

    private JLabel addQuantityLabel() {
        JLabel label = new JLabel("10" + " 개", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    private JLabel addDrinkLabel(String text) {
        JLabel label = new JLabel(text+" 원", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        label.setForeground(Color.RED); // 글자 색상 설정
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    private void addMoneyButton(String money) {
        JButton button = new JButton(money + " 원");
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Validation.billValid(money)) {    // 지폐가 5000원을 초과하는지 검증
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.OVER_5000.getText());
                    return;
                }
                if (!Validation.overMoney(payMoneyLabel.getText(), money)) {  // 전체 금액이 7000원을 초과하는지 검증
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.OVER_7000.getText());
                    return;
                }
                Validation.valid1000(money);    // 1000 원 인지 검증

                writer.println("money " + money); // 넣은 금액을 서버에 전송
                try {
                    updateAddMoney();// 넣은 금액 변환
                    payMoneyCheck();  // 구매 가능 음료 글자 색 변환
                } catch (IOException ex) {
                    throw new RuntimeException(ex); // Exception 만들어야 함!!
                }
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private void payMoneyCheck() {
        if (allPayMoney >= 450) {
            waterLabel.setForeground(Color.GREEN);
        } else {
            waterLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 500) {
            coffeeLabel.setForeground(Color.GREEN);
        } else {
            coffeeLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 550 ) {
            sportsDrinkLabel.setForeground(Color.GREEN);
        } else {
            sportsDrinkLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 700) {
            highQualityCoffeeLabel.setForeground(Color.GREEN);
        } else {
            highQualityCoffeeLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 750) {
            sodaLabel.setForeground(Color.GREEN);
        } else {
            sodaLabel.setForeground(Color.RED);
        }
        if (allPayMoney >= 800) {
            specialDrinkLabel.setForeground(Color.GREEN);
        } else {
            specialDrinkLabel.setForeground(Color.RED);
        }
    }

    private JLabel addMoneyLabel() {
        JLabel label = new JLabel("0 원", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    private void addPayBackButton(String text) {
        ImageIcon icon = new ImageIcon("image/" + text + ".png");
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                writer.println("return payBack"); // 넣은 금액을 서버에 전송
                try {
                    String remainMoney = reader.readLine();
                    allPayMoney = 0;
                    String remainText = readRemainMoney();
                    JOptionPane.showMessageDialog(vendingMachineFrame,
                            remainMoney + MessageTexts.PAYBACK.getText() + "\n" + remainText);
                    payMoneyLabel.setText(allPayMoney + " 원");
                    payMoneyCheck();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private String readRemainMoney() throws IOException {
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

    private void addAdminButton(SocketDto socketDto) {
        JButton button = new JButton("관리자 메뉴");
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginView.adminLoginView(socketDto);

            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    public static void vendingMachineView(SocketDto socketDto) throws IOException {
        new VendingMachineView(socketDto);
    }
}
