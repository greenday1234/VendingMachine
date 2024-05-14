package termProject.vendingmachine.exception;

import termProject.vendingmachine.message.ExceptionTexts;

public class MoneyException extends RuntimeException {

    public MoneyException(ExceptionTexts exceptionTexts) {
        super(exceptionTexts.getText());
    }
}
