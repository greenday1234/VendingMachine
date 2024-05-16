import View.VendingMachine_View;
import socket.MySocket;
import socket.SocketDto;

import java.io.IOException;

public class MyClient {

    private static final MySocket mySocket = new MySocket();

    public static void main(String[] args) throws IOException {

        SocketDto socketDto = mySocket.connectSocket();// 소켓 연결

        VendingMachine_View.vendingMachine_View(socketDto);  // 자판기 화면 실행



    }
}
