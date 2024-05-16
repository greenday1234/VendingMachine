package validate;

import message.MessageTexts;

public class Validate {

    public static String validWaterText(String readLine) {
        if (readLine == null) {
            return MessageTexts.NOT_BUY.getText();
        }
        return MessageTexts.POSSIBLE_BUY.getText();
    }

    public static String validCoffeeText(String readLine) {
        if (readLine == null) {
            return MessageTexts.NOT_BUY.getText();
        }
        return MessageTexts.POSSIBLE_BUY.getText();
    }

    public static String validSportsDrinkText(String readLine) {
        if (readLine == null) {
            return MessageTexts.NOT_BUY.getText();
        }
        return MessageTexts.POSSIBLE_BUY.getText();
    }

    public static String validHighQualityCoffeeText(String readLine) {
        if (readLine == null) {
            return MessageTexts.NOT_BUY.getText();
        }
        return MessageTexts.POSSIBLE_BUY.getText();
    }

    public static String validSodaText(String readLine) {
        if (readLine == null) {
            return MessageTexts.NOT_BUY.getText();
        }
        return MessageTexts.POSSIBLE_BUY.getText();
    }

    public static String validSpecialDrinkText(String readLine) {
        if (readLine == null) {
            return MessageTexts.NOT_BUY.getText();
        }
        return MessageTexts.POSSIBLE_BUY.getText();
    }
}