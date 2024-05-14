package termProject.vendingmachine.message;

import lombok.Getter;

@Getter
public enum ExceptionTexts {

    OVER_MONEY("금액이 너무 큽니다.");

    private String text;

    ExceptionTexts(String text) {
        this.text = text;
    }
}
