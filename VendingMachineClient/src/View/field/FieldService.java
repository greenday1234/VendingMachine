package View.field;

import javax.swing.*;
import java.awt.*;

import static View.frame.FrameService.adminLoginFrame;

public class FieldService {

    public static JPasswordField passwordField;

    public void addPasswordField() {
        passwordField = new JPasswordField(0);
        passwordField.setFont(new Font("굴림", Font.BOLD, 20));
        adminLoginFrame.getContentPane().add(passwordField);
        passwordField.setColumns(10);
    }
}
