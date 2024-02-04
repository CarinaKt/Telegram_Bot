package telegram_bot.Objects;

public class Stocks {
    private Stock[] stocks;
    
    public Stock[] getStocks() {
        return stocks;
    }

    public void add(String stock){
        // create a new array with one extra element
        Stock[] newStocks = new Stock[stocks.length + 1];

        // copy the elements from the old array to the new array
        System.arraycopy(stocks, 0, newStocks, 0, stocks.length);

        // add a new ChatID object to the end of the new array
        newStocks[newStocks.length - 1] = new Stock(stock);

        // update the reference to the new array
        stocks = newStocks;
    }

    public void remove(String stock){

    }
}
