package socket;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketDto {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public SocketDto(BufferedReader reader, PrintWriter writer, Socket socket) {
        this.reader = reader;
        this.writer = writer;
        this.socket = socket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public Socket getSocket() {
        return socket;
    }
}
