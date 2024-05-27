package View;

import View.button.ButtonService;
import View.frame.FrameService;
import View.label.LabelService;

import javax.swing.*;
import java.awt.*;

import static View.frame.FrameService.adminPWChangeFrame;

public class AdminPWChangeView {

    private FrameService frameService = new FrameService();
    private LabelService labelService = new LabelService();
    private ButtonService buttonService = new ButtonService();

    public static JPasswordField passwordChangeTextField;
    public static JPasswordField passwordTextField;

    public AdminPWChangeView() {

        frameService.addAdminPWChangeFrame();   // 프레임 생성

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3); // 여백 설정

        labelService.addPasswordLabel(gbc, 1, "기존 비밀번호");  // 비밀번호 label
        addPasswordField(gbc, 1);   // 비밀번호 field
        labelService.addPasswordLabel(gbc, 2, "새로운 비밀번호");  // 새 비밀번호 label
        addPasswordChangeField(gbc, 2); // 새 비밀번호 field
        buttonService.addPasswordChangeButton(gbc, 2);  // 비밀번호 변경 버튼 생성
        buttonService.addAdminBackButton(gbc, 0);    // 돌아가기 버튼

        adminPWChangeFrame.setVisible(true);
    }

    private void addPasswordChangeField(GridBagConstraints gbc, int row) {
        passwordChangeTextField = new JPasswordField("새로운 비밀번호 입력");
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordChangeTextField, gbc);
    }

    private void addPasswordField(GridBagConstraints gbc, int row) {
        passwordTextField = new JPasswordField("기존 비밀번호 입력");
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordTextField, gbc);
    }

    public static void adminPWChangeView() {
        new AdminPWChangeView();
    }
}
