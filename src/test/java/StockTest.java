import static org.junit.Assert.assertEquals;

import org.junit.Test;

import telegram_bot.Objects.Stock;
import telegram_bot.Objects.Stocks;

public class StockTest {

    Stocks stocks = new Stocks();

    @Test
    public void addStock(){

        stocks.add("ABC");
        Stock[] s = stocks.getStocks();
        
        assertEquals("ABC", s[0].getSymbol());
    }

    @Test
    public void removeSock(){

        stocks.add("ABC");
        stocks.remove("ABC");
        Stock[] s = stocks.getStocks();

        assertEquals(0, s.length);
    }

    @Test
    public void removeStock2(){
        stocks.add("ABC");
        stocks.remove("ABD");

        assertEquals(1, stocks.getStocks().length);

    }
    
}
