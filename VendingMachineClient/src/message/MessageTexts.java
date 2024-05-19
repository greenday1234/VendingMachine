package message;

public enum MessageTexts {

    WATER("450"),
    COFFEE("500"),
    SPORTSDRINK("550"),
    HIGHQUALITYCOFFEE("700"),
    SODA("750"),
    SPECIALDRINK("800"),
    WATER_SELL("물을 구매하셨습니다."),
    COFFEE_SELL("커피를 구매하셨습니다."),
    SPORTSDRINK_SELL("이온음료를 구매하셨습니다."),
    HIGHQUALITYCOFFEE_SELL("고급 커피를 구매하셨습니다."),
    SODA_SELL("탄산 음료를 구매하셨습니다."),
    SPECIALDRINK_SELL("특별 음료를 구매하셨습니다."),
    PASSWORD_ERROR("비밀번호가 잘못되었습니다. 다시 입력해주세요!"),
    OVER_5000("지폐를 5000원 초과하여 투입할 수 없습니다."),
    OVER_7000("7000원을 초과하여 투입할 수 없습니다."),
    QUANTITY_LACK("재고가 부족합니다."),
    CHANGE_MONEY_LACK("거슬러 줄 금액이 부족합니다. 죄송하지만 다른 동전이나 지폐를 넣어주세요."),
    MONEY_LACK("금액이 부족합니다."),
    PAYBACK(" 원을 반환하였습니다.");

    private String text;

    MessageTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
