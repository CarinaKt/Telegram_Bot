import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import telegram_bot.DBOperator;

public class ChatTestDB {

    DBOperator db = new DBOperator();

    @Test
    public void addChet(){

        Map<String, String> chatsMap = new HashMap<>();

        db.insertChats("test", 123456);

        chatsMap = db.readChats();

        assertTrue( chatsMap.containsValue("test"));
        assertTrue( chatsMap.containsKey("123456"));
    }

    @Test
    public void removeChet(){

        Map<String, String> chatsMap = new HashMap<>();

        db.insertChats("test", 123456);
        db.removeChats(123456);

        chatsMap = db.readChats();

        assertFalse(chatsMap.containsValue("test"));
        assertFalse( chatsMap.containsKey("123456"));
    }
}