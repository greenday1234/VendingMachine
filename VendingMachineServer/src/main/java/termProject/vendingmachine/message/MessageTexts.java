package termProject.vendingmachine.message;

import lombok.Getter;

@Getter
public enum MessageTexts {

    SOCKET_CONNECT("포트 {} 에서 클라이언트가 연결되었습니다.");

    private String text;

    MessageTexts(String text) {
        this.text = text;
    }
}
