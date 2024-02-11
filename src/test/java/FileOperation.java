import static org.junit.Assert.assertEquals;
import org.junit.Test;

import telegram_bot.FileOperations;
import telegram_bot.Objects.Chats;
import telegram_bot.Objects.Stocks;

public class FileOperation {

    

    @Test
    public void SaveChats(){
        Chats chats = new Chats();
        chats.add("test", "1234");
        chats.add("Hello", "653487");

        FileOperations f = new FileOperations();
        f.writeChats(chats);
        
    }

    @Test
    public void LoadChats(){
        Chats chats= new Chats();
        Chats m = new Chats();
        FileOperations f = new FileOperations();
        m = f.readChats();

        assertEquals(2, m.getChats().size());
    }


    
    @Test
    public void SaveStocks(){
        Stocks stocks = new Stocks();
        stocks.add("test");
        stocks.add("Hello");

        FileOperations f = new FileOperations();
        f.writeStocks(stocks);
        
    }

    @Test
    public void LoadStocks(){
        Stocks stocks = new Stocks();
        FileOperations f = new FileOperations();
        Stocks m = f.readStocks();

        assertEquals(2, m.getStocks().size());
    }
}
