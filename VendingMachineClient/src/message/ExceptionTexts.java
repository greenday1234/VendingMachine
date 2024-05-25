package message;

public enum ExceptionTexts {

    SOCKET_DISCONNECTION_FAIL("소켓을 종료하지 못했습니다.");

    private String text;

    ExceptionTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
