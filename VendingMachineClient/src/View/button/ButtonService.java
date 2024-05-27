package View.button;

import View.AdminLoginView;
import View.AdminPageView;
import View.label.LabelService;
import message.MessageTexts;
import socket.SocketDto;
import util.ReadService;
import validate.Validation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static View.VendingMachineView.*;
import static View.field.FieldService.passwordField;
import static View.frame.FrameService.adminLoginFrame;
import static View.frame.FrameService.vendingMachineFrame;
import static View.label.LabelService.payMoneyLabel;
import static util.Util.*;

public class ButtonService {

    private ReadService readService = new ReadService();
    private LabelService labelService = new LabelService();

    public void addDrinkButton(String text) {
        ImageIcon icon = new ImageIcon("image/" + text + ".png");
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(100, 100));

        button.addActionListener(e -> {
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
                readService.readUpdateQuantity();   // 재고 수량 읽기
                readService.readStockCheck();   // 재고 소진 일자 읽기
                labelService.checkTextLabel(text);   // label 변경

                updateDeleteMoney();// 넣은 금액 변경
                payMoneyCheck();  // 구매 가능 음료 글자 색 변환
                readService.readDailySales();
                readService.readMonthlySales();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    public void addMoneyButton(String money) {
        JButton button = new JButton(money + " 원");
        button.setPreferredSize(new Dimension(100, 50));

        button.addActionListener(e -> {
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
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    public void addPayBackButton() {
        ImageIcon icon = new ImageIcon("image/payBack.png");
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(100, 100));

        button.addActionListener(e -> {
            if (!Validation.validPayBack()) {
                    JOptionPane.showMessageDialog(vendingMachineFrame, MessageTexts.PAYBACK_LACK.getText());
                    return;
                }

                writer.println("return "); // 넣은 금액을 서버에 전송
                try {
                    String remainMoney = reader.readLine();
                    allPayMoney = 0;
                    String remainText = readService.readRemainMoney();
                    JOptionPane.showMessageDialog(vendingMachineFrame,
                            remainMoney + MessageTexts.PAYBACK.getText() + "\n" + remainText);
                    payMoneyLabel.setText(allPayMoney + " 원");
                    payMoneyCheck();
                    readService.readChangeMoney();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    public void addAdminButton(SocketDto socketDto) {
        JButton button = new JButton("관리자 메뉴");
        button.setPreferredSize(new Dimension(100, 50));

        button.addActionListener(e -> {
            AdminLoginView.adminLoginView(socketDto);
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    public void disConnectButton() {
        JButton button = new JButton("자판기 종료");
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(e -> {
            writer.println("exit");
            vendingMachineFrame.setVisible(false);
            disConnectSocket();
        });
        vendingMachineFrame.add(button);
    }

    public void addLoginButton(SocketDto socketDto) {
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 50));

        loginButton.addActionListener(e -> {
            if (passwordField.getText().equals(password.getPassword())) {
                vendingMachineFrame.setVisible(false);
                adminLoginFrame.setVisible(false);
                AdminPageView.adminPageView(socketDto);
            } else {
                JOptionPane.showMessageDialog(adminLoginFrame, MessageTexts.PASSWORD_ERROR.getText());
            }
        });
        adminLoginFrame.getContentPane().add(loginButton);
    }
}
