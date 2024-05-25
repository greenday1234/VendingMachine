package termProject.vendingmachine;


import termProject.vendingmachine.controller.SocketController;

public class VendingmachineApplication {

	public static void main(String[] args) {
		SocketController socketController = new SocketController();
		socketController.connectionSocket();	// 소켓 연결 진행
	}
}
