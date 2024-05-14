package termProject.vendingmachine.validate;

import termProject.vendingmachine.exception.MoneyException;
import termProject.vendingmachine.message.ExceptionTexts;

public class Validation {

    public void moneyValid(String inputLine) {
        int money = Integer.parseInt(inputLine);

        if(money > 7000) {
            throw new MoneyException(ExceptionTexts.OVER_MONEY);
        }
    }
}
