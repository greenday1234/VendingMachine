package message;

public enum MessageTexts {

    WATER("450"),
    COFFEE("500"),
    SPORTSDRINK("550"),
    HIGHQUALITYCOFFEE("700"),
    SODA("750"),
    SPECIALDRINK("800");

    private String text;

    MessageTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
