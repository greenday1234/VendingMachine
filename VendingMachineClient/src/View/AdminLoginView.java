package View;

import View.button.ButtonService;
import View.field.FieldService;
import View.frame.FrameService;
import View.label.LabelService;

import static View.frame.FrameService.adminLoginFrame;

public class AdminLoginView {

    private FrameService frameService = new FrameService();
    private LabelService labelService = new LabelService();
    private FieldService fieldService = new FieldService();
    private ButtonService buttonService = new ButtonService();

    public AdminLoginView() {

        frameService.addAdminLoginFrame();  // 프레임 생성
        labelService.addPWLabel();  // PW Label 생성
        fieldService.addPasswordField();    // PW Field 생성
        buttonService.addLoginButton();    // Login 버튼 생성

        adminLoginFrame.setVisible(true);
    }

    public static void adminLoginView() {
        new AdminLoginView();
    }
}
