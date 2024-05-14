import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "127.0.0.1";
        final int PORT = 8000;

        try (
                Socket socket = new Socket(SERVER_ADDRESS, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("서버에 연결되었습니다.");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                // 사용자 입력을 서버로 전송
                out.println(userInput);

                // 서버로부터 응답 받아 출력
                System.out.println("서버로부터 받은 메시지: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
