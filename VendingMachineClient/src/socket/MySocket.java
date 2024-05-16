package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocket {

    private final String SERVER_ADDRESS = "127.0.0.1";
    private final int PORT = 8000;

    public SocketDto connectSocket() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            return new SocketDto(reader, writer);
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}