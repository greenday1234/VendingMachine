package message;

public enum MessageTexts {

    WATER("450"),
    COFFEE("500"),
    SPORTSDRINK("550"),
    HIGHQUALITYCOFFEE("700"),
    SODA("750"),
    SPECIALDRINK("800"),
    PASSWORD_ERROR("비밀번호가 잘못되었습니다. 다시 입력해주세요!");

    private String text;

    MessageTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
