package termProject.vendingmachine.service;

import lombok.extern.slf4j.Slf4j;
import termProject.vendingmachine.VendingMachineThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class SocketService {

    public void connectionSocket() {

		final int PORT = 8000;

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트가 연결되었습니다.");

				// 클라이언트와 통신을 위한 스레드 생성
				new Thread(new VendingMachineThread(socket)).start();	// VendingMachineThread 에서 자판기 작업 진행
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
