import View.VendingMachineView;
import socket.MySocket;
import socket.SocketDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    private static final MySocket mySocket = new MySocket();

    public static void main(String[] args) throws IOException {

        SocketDto socketDto = mySocket.connectSocket();// 소켓 연결
        VendingMachineView.vendingMachineView(socketDto);  // 자판기 화면 실행
    }
}
