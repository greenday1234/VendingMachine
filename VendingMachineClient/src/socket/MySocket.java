package socket;

import java.io.*;
import java.net.Socket;

public class MySocket {

    private final String SERVER_ADDRESS = "172.30.1.44";
    private final int PORT = 8000;

    public SocketDto connectSocket() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            return new SocketDto(reader, writer, socket);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
