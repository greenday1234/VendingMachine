package termProject.vendingmachine.controller;

import termProject.vendingmachine.service.SocketService;

public class SocketController {

    private final SocketService socketService = new SocketService();

    public void connectionSocket() {
        socketService.connectionSocket();
    }

}
