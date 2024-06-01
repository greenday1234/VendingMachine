package View;

import View.button.ButtonService;
import View.frame.FrameService;
import View.label.LabelService;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static View.VendingMachineView.*;
import static View.frame.FrameService.adminPageFrame;
import static View.label.LabelService.*;
import static util.Util.*;

public class AdminPageView {

    private FrameService frameService = new FrameService();
    private ButtonService buttonService = new ButtonService();
    private LabelService labelService = new LabelService();

    public AdminPageView() {

        frameService.addAdminPageFrame();   // 프레임 생성

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3); // 여백 설정

        buttonService.addBackButton(gbc, 0);    // 뒤로가기 버튼 생성
        addChangeMoneyLabel(gbc);   // 남은 화폐 개수 label
        addChangeMenu(gbc); // 음료 이름 및 가격 변경
        addDailySales(gbc, 14); // 일별 매출 label
        addMonthlySales(gbc, 15);   // 월별 매출 label
        addCollectMoney(gbc, 16, "collectMoney");   // 수금
        buttonService.addPWChangeButton(gbc, 16);   // 관리자 비밀번호 변경 버튼 생성

        adminPageFrame.setVisible(true);
    }

    private void addChangeMenu(GridBagConstraints gbc) {
        int row = 2;

        for (int i = 0; i < 6; i++) {
            addChangeDrinkNamePriceRow(gbc, row++, i);
            addStockTribe(gbc, row++, i);
        }
    }

    private void addChangeMoneyLabel(GridBagConstraints gbc) {
        for (int i = 0; i < COIN_VALUES.size(); i++) {
            JLabel label = labelService.addMoneyQuantityLabel(gbc, i, 1, String.valueOf(COIN_VALUES.get(i)));
            CHANGE_MONEY_LABEL.add(label);
        }
    }

    private void addStockTribe(GridBagConstraints gbc, int row, int index) {
        JLabel stockLabel = labelService.addStockLabel();
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(stockLabel, gbc);

        JLabel stockCheckLabel = labelService.addStockCheckLabel(index);
        gbc.gridx = 1;
        gbc.gridy = row;
        adminPageFrame.add(stockCheckLabel, gbc);
    }

    private void addMonthlySales(GridBagConstraints gbc, int row) {
        JLabel monthSalesLabel = labelService.createAdminPageLabel("월별 매출");
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(monthSalesLabel, gbc);

        int index = 1;
        for (Map.Entry<String, Integer> entry : monthlySales.entrySet()) {
            labelService.addDailySaleLabel(gbc, row, index++, entry.getKey(), entry.getValue());
        }
    }

    private void addDailySales(GridBagConstraints gbc, int row) {
        JLabel daySalesLabel = labelService.createAdminPageLabel("일별 매출");
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(daySalesLabel, gbc);

        int index = 1;
        for (Map.Entry<String, Integer> entry : dailySales.entrySet()) {
            labelService.addDailySaleLabel(gbc, row, index++, entry.getKey(), entry.getValue());
        }
    }

    private void addCollectMoney(GridBagConstraints gbc, int row, String text) {
        JLabel moneyLabel = labelService.createAdminPageLabel("수금하기");
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(moneyLabel, gbc);

        ImageIcon icon = getImageIcon(text);
        buttonService.addCollectButton(gbc, icon);
    }

    private void addChangeDrinkNamePriceRow(GridBagConstraints gbc, int row, int index) {

        JLabel nameLabel = labelService.addAdminDrinkNameLabel(DRINK_LIST.get(index).getDrinkName());
        ADMIN_DRINK_NAME_LABEL.add(nameLabel);
        gbc.gridx = 0;
        gbc.gridy = row;
        adminPageFrame.add(nameLabel, gbc);

        JTextField nameTextField = new JTextField(nameLabel.getText(), 20);
        nameTextField.setPreferredSize(new Dimension(50, 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        adminPageFrame.add(nameTextField, gbc);

        setGbcConfig(gbc);

        JLabel priceLabel = new JLabel(DRINK_LIST.get(index).getDrinkPrice() + " 원");
        gbc.gridx = 2;
        adminPageFrame.add(priceLabel, gbc);

        JTextField priceTextField = new JTextField(Integer.toString(DRINK_LIST.get(index).getDrinkPrice()), 20);
        priceTextField.setPreferredSize(new Dimension(50, 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        adminPageFrame.add(priceTextField, gbc);

        setGbcConfig(gbc);

        JButton button = new JButton("변경");
        gbc.gridx = 4;
        button.addActionListener(e -> {
            check(index, priceLabel, nameTextField, priceTextField);
        });
        adminPageFrame.add(button, gbc);
    }

    private void setGbcConfig(GridBagConstraints gbc) {
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
    }

    public static void adminPageView() {
        new AdminPageView();
    }
}
