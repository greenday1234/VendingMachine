package View;

import message.MessageTexts;
import socket.SocketDto;
import validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class VendingMachine_View {

    private BufferedReader reader;
    private PrintWriter writer;

    private JLabel waterLabel;
    private JLabel coffeeLabel;
    private JLabel sportsDrinkLabel;
    private JLabel highQualityCoffeeLabel;
    private JLabel sodaLabel;
    private JLabel specialDrinkLabel;

    private static JFrame vendingMachineFrame;
    private static TextField IDField;
    private static JPasswordField passwordField;

    public VendingMachine_View(SocketDto socketDto) {

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

        waterLabel = addDrinkLabel();
        coffeeLabel = addDrinkLabel();
        sportsDrinkLabel = addDrinkLabel();
        highQualityCoffeeLabel = addDrinkLabel();
        sodaLabel = addDrinkLabel();
        specialDrinkLabel = addDrinkLabel();

        addMoneyButton("10");
        addMoneyButton("50");
        addMoneyButton("100");
        addMoneyButton("500");
        addMoneyButton("1000");


        vendingMachineFrame.setVisible(true);


        /*IDField = new TextField("");
        //IDField.setBounds(530,400,190,40);
        IDField.setFont(new Font("굴림", Font.BOLD, 20));
        vendingMachineFrame.getContentPane().add(IDField);
        IDField.setColumns(10);

        ImageIcon PassIcon = new ImageIcon("image/password.png");
        JLabel PWlabel = new JLabel(PassIcon,SwingConstants.RIGHT);
        //PWlabel.setBounds(400,460,115,40);
        vendingMachineFrame.getContentPane().add(PWlabel);

        passwordField = new JPasswordField(0);
        //PasswordField.setBounds(530,460,190,40);
        passwordField.setFont(new Font("굴림", Font.BOLD, 20));
        vendingMachineFrame.getContentPane().add(passwordField);
        passwordField.setColumns(10);*/


        /*waterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(IDField.getText().equals("test") && passwordField.getText().equals("1234")) {
                    vendingMachineFrame.setVisible(false);
                    //Select_View.Select_View();
                }
                else {
                    JOptionPane.showMessageDialog(vendingMachineFrame,"ID, 비밀번호를 다시 입력해주세요.");
                }
            }
        });*/
    }

    private void addDrinkButton(String text) {
        ImageIcon icon = new ImageIcon("image/" + text + ".png");
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * 이 부분에 구매 가능한지 검증하는 로직 추가해야함!!
                 */

                writer.println(text); // 버튼에 대한 메시지를 서버로 전송
                updateUI();
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
            waterLabel.setText(waterText);
            coffeeLabel.setText(coffeeText);
            sportsDrinkLabel.setText(sportsDrinkText);
            highQualityCoffeeLabel.setText(highQualityCoffeeText);
            sodaLabel.setText(sodaText);
            specialDrinkLabel.setText(specialDrinkText);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JLabel addDrinkLabel() {
        JLabel label = new JLabel(MessageTexts.NOT_BUY.getText(), SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
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
                 */

                writer.println(money); // 버튼에 대한 메시지를 서버로 전송
                updateUI();
            }
        });
        vendingMachineFrame.getContentPane().add(button);
    }

    public static void vendingMachine_View(SocketDto socketDto) throws IOException {
        new VendingMachine_View(socketDto);
    }
}
