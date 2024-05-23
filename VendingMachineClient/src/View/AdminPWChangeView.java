package View;

import message.MessageTexts;
import socket.SocketDto;
import validate.Validation;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;

import static View.VendingMachineView.password;

public class AdminPWChangeView {

    private BufferedReader reader;
    private PrintWriter writer;

    public JPasswordField passwordChangeTextField;
    public JPasswordField passwordTextField;

    public static JFrame adminPWChangeFrame;


    public AdminPWChangeView(SocketDto socketDto) {

        reader = socketDto.getReader();
        writer = socketDto.getWriter();

        // 프레임
        adminPWChangeFrame = new JFrame("비밀번호 변경");
        adminPWChangeFrame.setBounds(560,200,500,600);
        adminPWChangeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPWChangeFrame.getContentPane().setLayout(new GridBagLayout());
        adminPWChangeFrame.getContentPane().setBackground(new Color(255, 255, 255, 255));
        adminPWChangeFrame.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3); // 여백 설정

        addPasswordLabel(gbc, 0, 0);
        addPasswordField(gbc, 1, 0);
        addPasswordChangeLabel(gbc, 2, 0);
        addPasswordChangeField(gbc, 3, 0);
        addPasswordChangeButton(gbc, 4, 0);

        adminPWChangeFrame.setVisible(true);

    }

    private void addPasswordChangeButton(GridBagConstraints gbc, int row, int index) {
        JButton button = new JButton("변경하기");
        gbc.gridx = index;
        gbc.gridy = row;
        button.addActionListener(e -> {
            if (passwordTextField.getText().equals(password.getPassword())) {
                if (!Validation.validPW(passwordChangeTextField.getPassword().toString())) {
                    JOptionPane.showMessageDialog(adminPWChangeFrame, MessageTexts.FAIL_CHANGE_PW.getText());
                    return;
                }
                password.updatePassword(passwordChangeTextField.getPassword().toString());
                writer.println("password " + password.getPassword());
                JOptionPane.showMessageDialog(adminPWChangeFrame, MessageTexts.CHANGE_PW.getText());
                adminPWChangeFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(adminPWChangeFrame, MessageTexts.FAIL_SAME_PW.getText());
            }
        });
        adminPWChangeFrame.add(button, gbc);
    }

    private void addPasswordChangeField(GridBagConstraints gbc, int row, int index) {
        passwordChangeTextField = new JPasswordField("새로운 비밀번호를 입력해주세요.");
        gbc.gridx = index;
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordChangeTextField, gbc);
    }

    private void addPasswordChangeLabel(GridBagConstraints gbc, int row, int index) {
        JLabel passwordChangeLabel = new JLabel("새로운 비밀번호를 입력해주세요.");
        Font font = passwordChangeLabel.getFont();
        passwordChangeLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        passwordChangeLabel.setPreferredSize(new Dimension(130, 50));
        gbc.gridx = index;
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordChangeLabel, gbc);
    }

    private void addPasswordLabel(GridBagConstraints gbc, int row, int index) {
        JLabel passwordLabel = new JLabel("사용 중이던 비밀번호를 입력해주세요.");
        Font font = passwordLabel.getFont();
        passwordLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        passwordLabel.setPreferredSize(new Dimension(130, 50));
        gbc.gridx = index;
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordLabel, gbc);
    }

    private void addPasswordField(GridBagConstraints gbc, int row, int index) {
        passwordTextField = new JPasswordField("사용 중이던 비밀번호를 입력해주세요.");
        gbc.gridx = index;
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordTextField, gbc);
    }

    public static void adminPWChangeView(SocketDto socketDto) {
        new AdminPWChangeView(socketDto);
    }
}
