package socket;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class SocketDto {

    private BufferedReader reader;
    private PrintWriter writer;

    public SocketDto(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }
}
