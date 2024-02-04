import static org.junit.Assert.assertEquals;
import org.junit.Test;
import telegram_bot.Objects.Chat;
import telegram_bot.Objects.Chats;

public class ChatTest {

    Chats chats = new Chats();

    @Test
    public void addChetName(){

        chats.add("test", "123456");
        Chat[] c = chats.getChats();

        assertEquals("test", c[0].getName());
    }

    @Test
    public void addChetID(){

        chats.add("test", "123456");
        Chat[] c = chats.getChats();

        assertEquals("123456", c[0].getId());
    }

    @Test
    public void removeChet(){

        chats.add("test", "123456");
        chats.remove("123456");

        Chat[] c = chats.getChats();
        assertEquals(0, c.length);
    }

    @Test
    public void removeChet2(){
        
        chats.add("test", "123456");
        chats.remove("123457");

        Chat[] c = chats.getChats();
        assertEquals(1, c.length);
    }
}