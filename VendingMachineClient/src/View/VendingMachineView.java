package View;

import View.button.ButtonService;
import View.frame.FrameService;
import View.label.LabelService;
import login.Password;
import socket.SocketDto;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.List;

import static View.frame.FrameService.vendingMachineFrame;
import static View.label.LabelService.*;
import static util.Util.*;

public class VendingMachineView {

    public static BufferedReader reader;
    public static PrintWriter writer;
    public static Socket socket;
    
    private ButtonService buttonService = new ButtonService();
    private LabelService labelService = new LabelService();
    private FrameService frameService = new FrameService();
    
    public static Password password = new Password("");
    public static int allPayMoney = 0;
    public static final List<Integer> quantityList = new ArrayList<>(); // 재고 수량
    public static final List<Integer> changeMoney = new ArrayList<>();    // 거스름돈 수량
    public static final List<String> stockDate = new ArrayList<>(); // 재고 소진 일자
    public static int check1000 = 0;
    public static final TreeMap<String, Integer> dailySales = new TreeMap<>();
    public static final TreeMap<String, Integer> monthlySales = new TreeMap<>();

    public VendingMachineView(SocketDto socketDto) throws IOException {

        socketConfig(socketDto);    // 소켓 및 reader, writer 설정

        initProcess();  // 설정 값 초기화

        drinkListInit();    // DRINK_LIST 초기화

        frameService.makeVendingMachineFrame();  // 자판기 프레임 생성
        makeDrinkButton();  // 음료 버튼 생성
        addDrinkNameLabel();    // 음료 이름 label
        addDrinkQuantityLabel();    // 음료 수량 label
        addDrinkPriceLabel();   // 음료 가격 label
        addPayMoneyButton();    // 입금 버튼 생성
        buttonService.addPayBackButton();   // 환급 버튼 생성
        labelService.addMoneyLabel();   // 입금 금액 label
        buttonService.addAdminButton(socketDto);    // 관리자 버튼 생성
        buttonService.disConnectButton();   // 자판기 종료 버튼 생성

        vendingMachineFrame.setVisible(true);
    }

    private void addPayMoneyButton() {
        for (int i = 0; i < COIN_VALUES.size(); i++) {
            buttonService.addMoneyButton(COIN_VALUES.get(i).toString());
        }
    }

    private void addDrinkPriceLabel() {
        for (int i = 0; i < DRINK_LIST.size(); i++) {
            JLabel label = labelService.addDrinkPriceLabel(DRINK_LIST.get(i).getDrinkPrice());
            DRINK_PRICE_LABEL.add(label);
        }
    }

    private void addDrinkQuantityLabel() {
        for (int i = 0; i < DRINK_LIST.size(); i++) {
            JLabel label = labelService.addQuantityLabel(i);
            DRINK_QUANTITY_LABEL.add(label);
        }
    }

    private void addDrinkNameLabel() {
        for (int i = 0; i < DRINK_NAME_LABEL.size(); i++) {
            JLabel label = labelService.addNameLabel(DRINK_LIST.get(i).getDrinkName());
            DRINK_NAME_LABEL.add(label);
        }
    }

    private void makeDrinkButton() {
        for (int i = 0; i < DRINK.size(); i++) {
            buttonService.addDrinkButton(DRINK.get(i));
        }
    }

    private void socketConfig(SocketDto socketDto) {
        socket = socketDto.getSocket();
        reader = socketDto.getReader();
        writer = socketDto.getWriter();
    }

    public static void vendingMachineView(SocketDto socketDto) throws IOException {
        new VendingMachineView(socketDto);
    }
}
