package View;

import message.MessageTexts;
import socket.SocketDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class VendingMachineView {

    private BufferedReader reader;
    private PrintWriter writer;

    private JLabel waterLabel;
    private JLabel coffeeLabel;
    private JLabel sportsDrinkLabel;
    private JLabel highQualityCoffeeLabel;
    private JLabel sodaLabel;
    private JLabel specialDrinkLabel;

    public static JFrame vendingMachineFrame;

    public VendingMachineView(SocketDto socketDto) {

        reader = socketDto.getReader();
        writer = socketDto.getWriter();

        // 프레임
        vendingMachineFrame = new JFrame("자판기 프로그램");
        vendingMachineFrame.setBounds(560,200,790,300);
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

        addAdminButton(socketDto);

        vendingMachineFrame.setVisible(true);
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
                 */

            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private void updateUI() {
        try {
            // 서버로부터 데이터 수신
            String waterText = reader.readLine();
            String coffeeText = reader.readLine();
            String sportsDrinkText = reader.readLine();
            String highQualityCoffeeText = reader.readLine();
            String sodaText = reader.readLine();
            String specialDrinkText = reader.readLine();

            // UI 업데이트
            waterLabel.setForeground(Color.GREEN);
            coffeeLabel.setForeground(Color.GREEN);
            sportsDrinkLabel.setForeground(Color.GREEN);
            highQualityCoffeeLabel.setForeground(Color.GREEN);
            sodaLabel.setForeground(Color.GREEN);
            specialDrinkLabel.setForeground(Color.GREEN);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JLabel addDrinkLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        label.setForeground(Color.RED); // 글자 색상 설정
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    private void addMoneyButton(String money) {
        JButton button = new JButton(money+"원");
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * 이 부분에 전체 금액이 7000원을 넘는지 검증하고, 7000원 넘으면 팝업 띄워야 함!!
                 * 음료 구매 가능 시 글자 색 변경해야함!!
                 */

                writer.println(money); // 버튼에 대한 메시지를 서버로 전송
                updateUI();
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    private void addAdminButton(SocketDto socketDto) {
        JButton button = new JButton("관리자 메뉴");
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * 이 부분에 비밀번호를 입력할 수 있는 페이지로 넘어가는 로직 작성해야 함!!
                 */
                //vendingMachineFrame.setVisible(false);
                AdminLoginView.adminLoginView(socketDto);

            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    public static void vendingMachineView(SocketDto socketDto) {
        new VendingMachineView(socketDto);
    }
}
