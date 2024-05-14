package termProject.vendingmachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class VendingMachineThread implements Runnable {
    private Socket socket;

    public VendingMachineThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            /**
             * 테스트 코드!! 추후 프론트로 변경해야 할 부분
             */
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                // 클라이언트로부터 받은 요청 처리
                System.out.println("클라이언트로부터 받은 메시지: " + inputLine);

                // 자판기 동작에 따른 응답 전송
                String response = processRequest(inputLine);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        // 요청에 따른 작업 처리
        // 예: "Coke"를 요청받으면 재고 확인 후 응답
        if (request.equals("Coke")) {
            // 자판기의 재고 확인 로직
            return "Coke을 제공합니다.";
        } else {
            return "지원하지 않는 상품입니다.";
        }
    }
}