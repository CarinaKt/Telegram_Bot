import static org.junit.Assert.assertEquals;
import org.junit.Test;
import telegram_bot.Objects.Chat;
import telegram_bot.Objects.Chats;

public class ChatTest {

    Chats chat = new Chats();

    @Test
    public void addChetName(){
        chat.add("test", "123456");
        Chat[] c = chat.getChats();

        assertEquals("test", c[0].getName());
    }

    @Test
    public void addChetID(){
        chat.add("test", "123456");
        Chat[] c = chat.getChats();

        assertEquals("123456", c[0].getId());
    }

    @Test
    public void removeChet(){
        chat.add("test", "123456");
        chat.remove("123456");

        Chat[] c = chat.getChats();
        assertEquals(0, c.length);
    }

    @Test
    public void removeChet2(){
        chat.add("test", "123456");
        chat.remove("123457");

        Chat[] c = chat.getChats();
        assertEquals(1, c.length);
    }
}