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

    private int allPayMoney = 0;
    private final List<Integer> payMoney; // 입력 금액
    private final List<Integer> quantityList; // 재고 수량
    private final List<Integer> changeMoney;    // 거스름돈 수량

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

        payMoney = new ArrayList<>();
        quantityList = new ArrayList<>();
        changeMoney = new ArrayList<>();

        payMoneyInit(); // 입력 금액 초기화
        quantityListInit(); // 재고 수량 초기화
        changeMoneyInit();  // 거스름돈 수량 초기화

        // 프레임
        vendingMachineFrame = new JFrame("자판기 프로그램");
        vendingMachineFrame.setBounds(560,200,790,400);
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

    private void payMoneyInit() throws IOException {
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            payMoney.add(Integer.parseInt(str));
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
                /**
                 * 이 부분에 구매 가능한지 검증하는 로직 추가해야함!!(거스름돈)
                 */

                if (!Validation.validQuantity(text, quantityList)) { // 재고 검증
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.QUANTITY_LACK.getText());
                    return;
                }
                if () {

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
                waterQuantityLabel.setText(Integer.toString(quantityList.get(0)));
                break;
            case "coffee":
                coffeeQuantityLabel.setText(Integer.toString(quantityList.get(1)));
                break;
            case "sportsDrink":
                sportsDrinkQuantityLabel.setText(Integer.toString(quantityList.get(2)));
                break;
            case "highQualityCoffee":
                highQualityCoffeeQuantityLabel.setText(Integer.toString(quantityList.get(3)));
                break;
            case "soda":
                sodaQuantityLabel.setText(Integer.toString(quantityList.get(4)));
                break;
            case "specialDrink":
                specialDrinkQuantityLabel.setText(Integer.toString(quantityList.get(5)));
                break;

        }
    }

    private int updateAddMoney(String money) throws IOException {
        addPayMoney(money);
        String payMoneyResult = reader.readLine();
        payMoneyLabel.setText(payMoneyResult + " 원");
        return Integer.parseInt(payMoneyResult);
    }

    private void updateDeleteMoney() throws IOException {
        String payMoneyResult = reader.readLine();
        allPayMoney = Integer.parseInt(payMoneyResult);
        payMoneyLabel.setText(allPayMoney + " 원");
    }

    private void deletePayMoney(String text) {
        switch (text) {
            case "water":
                allPayMoney -= 450;
                break;
            case "coffee":
                allPayMoney -= 500;
                break;
            case "sportsDrink":
                allPayMoney -= 550;
                break;
            case "highQualityCoffee":
                allPayMoney -= 700;
                break;
            case "soda":
                allPayMoney -= 750;
                break;
            case "specialDrink":
                allPayMoney -= 800;
                break;
        }
    }

    private void addPayMoney(String money) {
        switch (money) {
            case "10":
                Integer ten = payMoney.get(0);
                ten++;
                payMoney.set(0, ten);
                break;
            case "50":
                Integer fif = payMoney.get(1);
                fif++;
                payMoney.set(1, fif);
                break;
            case "100":
                Integer hun = payMoney.get(2);
                hun++;
                payMoney.set(2, hun);
                break;
            case "500":
                Integer fivehun = payMoney.get(3);
                fivehun++;
                payMoney.set(3, fivehun);
                break;
            case "1000":
                Integer thuo = payMoney.get(4);
                thuo++;
                payMoney.set(4, thuo);
                break;
        }
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
                if (!Validation.billValid(payMoney, money)) {    // 지폐가 5000원을 초과하는지 검증
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.OVER_5000.getText());
                    return;
                }
                if (!Validation.overMoney(payMoneyLabel.getText(), money)) {  // 전체 금액이 7000원을 초과하는지 검증
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.OVER_7000.getText());
                    return;
                }

                writer.println("money " + money); // 넣은 금액을 서버에 전송
                try {
                    allPayMoney = updateAddMoney(money);// 넣은 금액 변환
                    updateAddPayMoney();   // PayMoney 리스트 업데이트
                    payMoneyCheck();  // 구매 가능 음료 글자 색 변환
                } catch (IOException ex) {
                    throw new RuntimeException(ex); // Exception 만들어야 함!!
                }
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private void updateAddPayMoney() throws IOException {
        payMoney.clear();
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            payMoney.add(Integer.parseInt(str));
        }
    }

    private void payMoneyCheck() {
        if (allPayMoney >= 450 && Validation.quantityCheck(waterQuantityLabel.getText())) {
            waterLabel.setForeground(Color.GREEN);
        }
        if (allPayMoney >= 500 && Validation.quantityCheck(coffeeQuantityLabel.getText())) {
            coffeeLabel.setForeground(Color.GREEN);
        }
        if (allPayMoney >= 550 && Validation.quantityCheck(sportsDrinkQuantityLabel.getText())) {
            sportsDrinkLabel.setForeground(Color.GREEN);
        }
        if (allPayMoney >= 700 && Validation.quantityCheck(highQualityCoffeeQuantityLabel.getText())) {
            highQualityCoffeeLabel.setForeground(Color.GREEN);
        }
        if (allPayMoney >= 750 && Validation.quantityCheck(sodaQuantityLabel.getText())) {
            sodaLabel.setForeground(Color.GREEN);
        }
        if (allPayMoney >= 800 && Validation.quantityCheck(specialDrinkQuantityLabel.getText())) {
            specialDrinkLabel.setForeground(Color.GREEN);
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
