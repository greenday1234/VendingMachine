package View.frame;

import javax.swing.*;
import java.awt.*;

public class FrameService {

    public static JFrame vendingMachineFrame;
    public static JFrame adminLoginFrame;

    public void makeVendingMachineFrame() {
        vendingMachineFrame = new JFrame("자판기 프로그램");
        vendingMachineFrame.setBounds(560,200,790,600);
        vendingMachineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendingMachineFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        vendingMachineFrame.getContentPane().setBackground(new Color(151, 100, 100));
        vendingMachineFrame.setLocationRelativeTo(null);
    }

    public void addAdminLoginFrame() {
        adminLoginFrame = new JFrame("관리자 메뉴 로그인");
        adminLoginFrame.setBounds(560,200,300,200);
        adminLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLoginFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        adminLoginFrame.getContentPane().setBackground(new Color(255, 255, 255));
        adminLoginFrame.setLocationRelativeTo(null);
    }
}
