package View.label;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static View.VendingMachineView.*;
import static View.frame.FrameService.adminLoginFrame;
import static View.frame.FrameService.vendingMachineFrame;
import static util.Util.DRINK;
import static util.Util.DRINK_LIST;

public class LabelService {

    public static final List<JLabel> DRINK_NAME_LABEL = new ArrayList<>();
    public static final List<JLabel> DRINK_QUANTITY_LABEL = new ArrayList<>();
    public static final List<JLabel> DRINK_PRICE_LABEL = new ArrayList<>();

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
}
