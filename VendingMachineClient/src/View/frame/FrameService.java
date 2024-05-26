package View.frame;

import javax.swing.*;
import java.awt.*;

import static View.VendingMachineView.vendingMachineFrame;

public class FrameService {

    public static void makeVendingMachineFrame() {
        vendingMachineFrame = new JFrame("자판기 프로그램");
        vendingMachineFrame.setBounds(560,200,790,600);
        vendingMachineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendingMachineFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        vendingMachineFrame.getContentPane().setBackground(new Color(151, 100, 100));
        vendingMachineFrame.setLocationRelativeTo(null);
    }
}
