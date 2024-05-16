package socket;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketDto {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketDto(BufferedReader reader, PrintWriter writer, ObjectInputStream in, ObjectOutputStream out, Socket socket) {
        this.reader = reader;
        this.writer = writer;
        this.in = in;
        this.out = out;
        this.socket = socket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public Socket getSocket() {
        return socket;
    }
}
