package termProject.vendingmachine.message;

import lombok.Getter;

@Getter
public enum ExceptionTexts {

    SOCKET_FAIL("소켓 연결에 실패했습니다."),
    EXCEPTION("클라이언트 처리 중 오류 발생");

    private String text;

    ExceptionTexts(String text) {
        this.text = text;
    }
}
