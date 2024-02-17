import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import telegram_bot.DBOperator;

public class StockTestDB {

    DBOperator db = new DBOperator();

    @Test
    public void addStock(){

        db.insertStock("ABC");
        List<String> stocks = db.readStocks();

        assertEquals(true, stocks.contains("ABC"));
    }

    @Test
    public void removeSock(){

        db.insertStock("ABC");
        db.removeStock("ABC");
        
        List<String> stocks = db.readStocks();
        assertFalse(stocks.contains("ABC"));
    }
    
}
