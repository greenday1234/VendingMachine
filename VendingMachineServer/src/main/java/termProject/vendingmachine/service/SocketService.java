package termProject.vendingmachine.service;

import lombok.extern.slf4j.Slf4j;
import termProject.vendingmachine.VendingMachineThread;
import termProject.vendingmachine.domain.VendingMachine;
import termProject.vendingmachine.message.ExceptionTexts;
import termProject.vendingmachine.message.MessageTexts;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class SocketService {

    public void connectionSocket() {

		final int PORT1 = 8000;
		final int PORT2 = 9000;

		try {
			ServerSocket serverSocket1 = new ServerSocket(PORT1);
			ServerSocket serverSocket2 = new ServerSocket(PORT2);
			System.out.println("서버가 시작되었습니다.");

			VendingMachine vendingMachine1 = new VendingMachine();
			VendingMachine vendingMachine2 = new VendingMachine();

			new Thread(() -> acceptClients(serverSocket1, PORT1, vendingMachine1)).start();
			new Thread(() -> acceptClients(serverSocket2, PORT2, vendingMachine2)).start();

		} catch (IOException e) {
			log.error(ExceptionTexts.SOCKET_FAIL.getText(), e);
		}
	}

	private void acceptClients(ServerSocket serverSocket, int port, VendingMachine vendingMachine) {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				log.info(MessageTexts.SOCKET_CONNECT.getText(), port);
				new Thread(new VendingMachineThread(socket, port, vendingMachine)).start();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
