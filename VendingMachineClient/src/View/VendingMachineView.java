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
                 * 이 부분에 구매 가능한지 검증하는 로직 추가해야함!!(금액, 남은 재고 등)
                 * 거스름돈 계산도 가능한지 확인해야함!!
                 */



                writer.println("drink " + text);   // 서버에 전송
                checkTextLabel(text);



            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private void checkTextLabel(String text) {
        switch (text) {
            case "water":
                Integer water = quantityList.get(0);
                quantityList.set(0, --water);
                waterQuantityLabel.setText(Integer.toString(quantityList.get(0)));
                break;
            case "coffee":
                Integer coffee = quantityList.get(1);
                quantityList.set(1, --coffee);
                coffeeQuantityLabel.setText(Integer.toString(quantityList.get(1)));
                break;
            case "sportsDrink":
                Integer sportsDrink = quantityList.get(2);
                quantityList.set(2, --sportsDrink);
                sportsDrinkQuantityLabel.setText(Integer.toString(quantityList.get(2)));
                break;
            case "highQualityCoffee":
                Integer highQualityCoffee = quantityList.get(3);
                quantityList.set(3, --highQualityCoffee);
                highQualityCoffeeQuantityLabel.setText(Integer.toString(quantityList.get(3)));
                break;
            case "soda":
                Integer soda = quantityList.get(4);
                quantityList.set(4, --soda);
                sodaQuantityLabel.setText(Integer.toString(quantityList.get(4)));
                break;
            case "specialDrink":
                Integer specialDrink = quantityList.get(5);
                quantityList.set(5, --specialDrink);
                specialDrinkQuantityLabel.setText(Integer.toString(quantityList.get(5)));
                break;

        }
    }

    private int updateMoney(String money) throws IOException {
        addPayMoney(money);
        String payMoneyResult = reader.readLine();
        payMoneyLabel.setText(payMoneyResult + " 원");
        return Integer.parseInt(payMoneyResult);
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
                    allPayMoney = updateMoney(money);// 넣은 금액 변환
                    updatePayMoney();   // PayMoney 리스트 업데이트
                    payMoneyCheck(allPayMoney);  // 구매 가능 음료 글자 색 변환
                } catch (IOException ex) {
                    throw new RuntimeException(ex); // Exception 만들어야 함!!
                }
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private void updatePayMoney() throws IOException {
        payMoney.clear();
        for (int i = 0; i < 5; i++) {
            String str = reader.readLine();
            payMoney.add(Integer.parseInt(str));
        }
    }

    private void payMoneyCheck(int result) {
        if (result >= 450 && Validation.quantityCheck(waterQuantityLabel.getText())) {
            waterLabel.setForeground(Color.GREEN);
        }
        if (result >= 500 && Validation.quantityCheck(coffeeQuantityLabel.getText())) {
            coffeeLabel.setForeground(Color.GREEN);
        }
        if (result >= 550 && Validation.quantityCheck(sportsDrinkQuantityLabel.getText())) {
            sportsDrinkLabel.setForeground(Color.GREEN);
        }
        if (result >= 700 && Validation.quantityCheck(highQualityCoffeeQuantityLabel.getText())) {
            highQualityCoffeeLabel.setForeground(Color.GREEN);
        }
        if (result >= 750 && Validation.quantityCheck(sodaQuantityLabel.getText())) {
            sodaLabel.setForeground(Color.GREEN);
        }
        if (result >= 800 && Validation.quantityCheck(specialDrinkQuantityLabel.getText())) {
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
