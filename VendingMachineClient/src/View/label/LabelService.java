package View.label;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static View.VendingMachineView.*;
import static View.frame.FrameService.*;
import static util.Util.DRINK;
import static util.Util.DRINK_LIST;

public class LabelService {

    public static final List<JLabel> DRINK_NAME_LABEL = new ArrayList<>();
    public static final List<JLabel> DRINK_QUANTITY_LABEL = new ArrayList<>();
    public static final List<JLabel> DRINK_PRICE_LABEL = new ArrayList<>();
    public static final List<JLabel> CHANGE_MONEY_LABEL = new ArrayList<>();
    public static final List<JLabel> ADMIN_DRINK_NAME_LABEL = new ArrayList<>();

    public static JLabel payMoneyLabel;

    public void addMoneyLabel() {
        payMoneyLabel = new JLabel(allPayMoney + " 원", SwingConstants.CENTER);
        Font font = payMoneyLabel.getFont();
        payMoneyLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        payMoneyLabel.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(payMoneyLabel);
    }

    public JLabel addNameLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    public JLabel addAdminDrinkNameLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        return label;
    }

    public JLabel addQuantityLabel(int index) {
        JLabel label = new JLabel(DRINK_LIST.get(index).getQuantity() + " 개", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    public JLabel addDrinkPriceLabel(int price) {
        JLabel label = new JLabel(price + " 원", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20));
        label.setPreferredSize(new Dimension(100, 50));
        label.setForeground(Color.RED); // 글자 색상 설정
        vendingMachineFrame.getContentPane().add(label);
        return label;
    }

    public void checkTextLabel(String text) {
        for (int i = 0; i < DRINK.size(); i++) {
            if (text.equals(DRINK.get(i))) {
                DRINK_QUANTITY_LABEL.get(i).setText(DRINK_LIST.get(i).getQuantity() + " 개");
                JOptionPane.showMessageDialog(vendingMachineFrame, DRINK_LIST.get(i).getDrinkName() + " 을 구매하셨습니다.");
                return;
            }
        }
    }

    public void addPWLabel() {
        JLabel passwordLabel = new JLabel("PW", SwingConstants.CENTER);
        Font font = passwordLabel.getFont();
        passwordLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        passwordLabel.setPreferredSize(new Dimension(100, 50));
        passwordLabel.setForeground(Color.RED); // 글자 색상 설정
        adminLoginFrame.getContentPane().add(passwordLabel);
    }

    public JLabel addMoneyQuantityLabel(GridBagConstraints gbc, int index, int row, String text) {
        JLabel label = new JLabel(text + " 원 " + changeMoney.get(index) + " 개", SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 15));
        label.setPreferredSize(new Dimension(130, 50));
        gbc.gridx = index;
        gbc.gridy = row;
        adminPageFrame.add(label, gbc);
        return label;
    }

    public JLabel createAdminPageLabel(String text) {
        JLabel daySalesLabel = new JLabel(text);
        Font font = daySalesLabel.getFont();
        daySalesLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        daySalesLabel.setPreferredSize(new Dimension(70, 50));
        return daySalesLabel;
    }

    public void addDailySaleLabel(GridBagConstraints gbc, int row, int index, String key, Integer value) {
        JLabel daySalesLabel = new JLabel(key + " " + value);
        Font font = daySalesLabel.getFont();
        daySalesLabel.setFont(font.deriveFont(Font.PLAIN, 20));
        daySalesLabel.setPreferredSize(new Dimension(70, 50));
        gbc.gridx = index;
        gbc.gridy = row;
        adminPageFrame.add(daySalesLabel, gbc);
    }

    public JLabel addStockLabel() {
        JLabel stockLabel = new JLabel("재고");
        Font font = stockLabel.getFont();
        stockLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        stockLabel.setPreferredSize(new Dimension(50, 50));
        return stockLabel;
    }

    public JLabel addStockCheckLabel(int index) {
        JLabel stockCheckLabel;
        if (stockDate.get(index).equals("0")) {
            stockCheckLabel = new JLabel(DRINK_LIST.get(index).getQuantity() + " 개");
        } else {
            stockCheckLabel = new JLabel(stockDate.get(index) + "  소진");
        }
        Font checkFont = stockCheckLabel.getFont();
        stockCheckLabel.setFont(checkFont.deriveFont(Font.PLAIN, 15));
        stockCheckLabel.setPreferredSize(new Dimension(50, 50));
        return stockCheckLabel;
    }

    public void addPasswordLabel(GridBagConstraints gbc, int row, String text) {
        JLabel passwordLabel = new JLabel(text);
        Font font = passwordLabel.getFont();
        passwordLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        passwordLabel.setPreferredSize(new Dimension(150, 50));
        gbc.gridy = row;
        adminPWChangeFrame.add(passwordLabel, gbc);
    }
}
