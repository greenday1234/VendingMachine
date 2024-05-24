package View;

import message.MessageTexts;
import socket.SocketDto;
import validate.Validation;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

import static View.VendingMachineView.password;

public class AdminPWChangeView {

    private PrintWriter writer;

    public JPasswordField passwordChangeTextField;
    public JPasswordField passwordTextField;

    public static JFrame adminPWChangeFrame;


    public AdminPWChangeView(SocketDto socketDto) {
        writer = socketDto.getWriter();

        // 프레임
        adminPWChangeFrame = new JFrame("비밀번호 변경");
        adminPWChangeFrame.setBounds(560,200,400,200);
        adminPWChangeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPWChangeFrame.getContentPane().setLayout(new GridBagLayout());
        adminPWChangeFrame.getContentPane().setBackground(new Color(255, 255, 255, 255));
        adminPWChangeFrame.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3); // 여백 설정

        addPasswordLabel(gbc, 1);
        addPasswordField(gbc, 1);
        addPasswordChangeLabel(gbc, 2);
        addPasswordChangeField(gbc, 2);
        addPasswordChangeButton(gbc, 2);

        addBackButton(gbc, 0);

        adminPWChangeFrame.setVisible(true);
    }

    private void addBackButton(GridBagConstraints gbc, int row) {
        JButton button = new JButton("창 닫기");
        button.setPreferredSize(new Dimension(50, 40));
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        button.addActionListener(e -> {
            adminPWChangeFrame.setVisible(false);
        });
        adminPWChangeFrame.add(button, gbc);
    }

    private void addPasswordChangeButton(GridBagConstraints gbc, int row) {
        JButton button = new JButton("변경하기");
        button.setPreferredSize(new Dimension(100, 50));
        gbc.gridy = row;
        button.addActionListener(e -> {
            if (passwordTextField.getText().equals(password.getPassword())) {
                if (!Validation.validPW(passwordChangeTextField.getText())) {
                    JOptionPane.showMessageDialog(adminPWChangeFrame, MessageTexts.FAIL_CHANGE_PW.getText());
                    return;
                }
                password.updatePassword(passwordChangeTextField.getText());
                writer.println("password " + password.getPassword());
                JOptionPane.showMessageDialog(adminPWChangeFrame, MessageTexts.CHANGE_PW.getText());
                adminPWChangeFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(adminPWChangeFrame, MessageTexts.FAIL_SAME_PW.getText());
            }
        });
        adminPWChangeFrame.add(button, gbc);
    }

    private void addPasswordChangeField(GridBagConstraints gbc, int row) {
        passwordChangeTextField = new JPasswordField("새로운 비밀번호 입력");
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordChangeTextField, gbc);
    }

    private void addPasswordChangeLabel(GridBagConstraints gbc, int row) {
        JLabel passwordChangeLabel = new JLabel("새로운 비밀번호");
        Font font = passwordChangeLabel.getFont();
        passwordChangeLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        passwordChangeLabel.setPreferredSize(new Dimension(150, 50));
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordChangeLabel, gbc);
    }

    private void addPasswordLabel(GridBagConstraints gbc, int row) {
        JLabel passwordLabel = new JLabel("기존 비밀번호");
        Font font = passwordLabel.getFont();
        passwordLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        passwordLabel.setPreferredSize(new Dimension(150, 50));
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordLabel, gbc);
    }

    private void addPasswordField(GridBagConstraints gbc, int row) {
        passwordTextField = new JPasswordField("기존 비밀번호 입력");
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordTextField, gbc);
    }

    public static void adminPWChangeView(SocketDto socketDto) {
        new AdminPWChangeView(socketDto);
    }
}
