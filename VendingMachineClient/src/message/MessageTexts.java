package message;

public enum MessageTexts {

    NOT_BUY("구매 불가"),
    POSSIBLE_BUY("구매 가능");

    private String text;

    MessageTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
