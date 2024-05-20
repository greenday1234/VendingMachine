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
        adminPageFrame.setBounds(560,200,790,500);
        adminPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPageFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        adminPageFrame.getContentPane().setBackground(new Color(255, 255, 255, 255));
        adminPageFrame.setLocationRelativeTo(null);

        /**
         * 1. 화폐 현황
         * 2. 수금메뉴 (반환을 위한 최소한의 화폐는 임의로 지정해 남김)
         * 3. 각 음료의 판매 가격 (변경 가능)
         * 4. 판매 이름 (변경 가능)
         * 5. 일별/월별 매출
         * 6. 재고 소진 날짜
         * 7. 비밀번호 설정
         */

        // 화폐 현황


        adminPageFrame.setVisible(true);
    }

    public static void adminPageView(SocketDto socketDto) {
        new AdminPageView(socketDto);
    }
}
