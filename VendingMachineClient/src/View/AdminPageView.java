package View;

import socket.SocketDto;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class AdminPageView {

    private BufferedReader reader;
    private PrintWriter writer;

    private JFrame adminPageFrame;

    public AdminPageView(SocketDto socketDto) {

        reader = socketDto.getReader();
        writer = socketDto.getWriter();

        // 프레임
        adminPageFrame = new JFrame("관리자 페이지");
        adminPageFrame.setBounds(560,200,790,300);
        adminPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPageFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        adminPageFrame.getContentPane().setBackground(new Color(104, 255, 164));
        adminPageFrame.setLocationRelativeTo(null);

        adminPageFrame.setVisible(true);
    }

    public static void adminPageView(SocketDto socketDto) {
        new AdminPageView(socketDto);
    }
}
