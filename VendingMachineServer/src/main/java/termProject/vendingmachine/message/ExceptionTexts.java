package termProject.vendingmachine.message;

import lombok.Getter;

@Getter
public enum ExceptionTexts {

    SOCKET_FAIL("소켓 연결에 실패했습니다.");

    private String text;

    ExceptionTexts(String text) {
        this.text = text;
    }
}
