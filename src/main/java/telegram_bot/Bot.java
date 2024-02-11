package telegram_bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram_bot.Objects.Chats;
import telegram_bot.Objects.Stocks;

public class Bot extends TelegramLongPollingBot {

  private String botName;
  private Chats chat;
  private Stocks stock;

  // TODO: Errormessages to user
  // TODO: sendMessage in own method
  public Bot(String botName, Chats chat, Stocks stock) {

    super(System.getenv("BOT_TOKEN"));
    this.botName = botName;
    this.chat = chat;
    this.stock = stock;
  }

  @Override
  public void onUpdateReceived(Update update) {

    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      // Set variables
      String messageText = update.getMessage().getText();
      String name = update.getMessage().getFrom().getFirstName();
      String chatId = update.getMessage().getChatId().toString();
      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(chatId);
      messageText = messageText.replace("/", "");

      if (messageText.equalsIgnoreCase("start")) {

        this.chat.add(messageText, chatId);

        sendMessage.setText("Hello " + name + ", added your ID: " + chatId + " to the Brodcast");

        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      } else if (messageText.contains("add")) {

        String stockSymbol = messageText.toUpperCase().replaceAll("ADD", "").replace("\\", "").strip();
        this.stock.add(stockSymbol);

        sendMessage.setText("Added " + stockSymbol + " to watch list");

        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      } else if (messageText.equalsIgnoreCase("rm")) {

        String stockSymbol = messageText.toUpperCase().replaceAll("RM", "").replace("\\", "").strip();
        this.stock.remove(stockSymbol);

        sendMessage.setText("Removed " + stock + " from watch list");

        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      } else if (messageText.equalsIgnoreCase("stop")) {

        chat.remove(chatId);

        sendMessage.setText("Removed your ID: " + chatId );

        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void sendMessage(String messagen, String chat_id) {

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chat_id);
    sendMessage.setText(messagen);
    
    try {
      execute(sendMessage); // Sending our message object to user
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getBotUsername() {
    // Return bot username
    return this.botName;
  }

  @Override
  public String getBotToken() {
    // Return bot token from BotFather
    return System.getenv("BOT_TOKEN");
  }
}
