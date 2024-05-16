package View;

import message.MessageTexts;
import socket.SocketDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

import static View.VendingMachineView.vendingMachineFrame;

public class AdminLoginView {

    private static JFrame adminLoginFrame;
    private static JPasswordField passwordField;

    public AdminLoginView(SocketDto socketDto) {

        // 프레임
        adminLoginFrame = new JFrame("관리자 메뉴 로그인");
        adminLoginFrame.setBounds(560,200,300,300);
        adminLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLoginFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        adminLoginFrame.getContentPane().setBackground(new Color(255, 255, 255));
        adminLoginFrame.setLocationRelativeTo(null);

        JLabel passwordLabel = new JLabel("PW", SwingConstants.CENTER);
        Font font = passwordLabel.getFont();
        passwordLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        passwordLabel.setPreferredSize(new Dimension(100, 50));
        passwordLabel.setForeground(Color.RED); // 글자 색상 설정
        adminLoginFrame.getContentPane().add(passwordLabel);

        passwordField = new JPasswordField(0);
        passwordField.setFont(new Font("굴림", Font.BOLD, 20));
        adminLoginFrame.getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 100));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getText().equals("1234")) {
                    vendingMachineFrame.setVisible(false);
                    adminLoginFrame.setVisible(false);
                    AdminPageView.adminPageView(socketDto);
                } else {
                    JOptionPane.showMessageDialog(adminLoginFrame, MessageTexts.PASSWORD_ERROR.getText());
                }

            }
        });
        adminLoginFrame.getContentPane().add(loginButton);

        adminLoginFrame.setVisible(true);
    }

    public static void adminLoginView(SocketDto socketDto) {
        new AdminLoginView(socketDto);
    }
}
