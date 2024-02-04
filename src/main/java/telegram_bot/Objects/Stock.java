package telegram_bot.Objects;

public class Stock {
    private String symbol;

    public Stock(String symbol){
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }

}