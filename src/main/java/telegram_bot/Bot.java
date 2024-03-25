package telegram_bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

  private String botName;
  private DBOperator db;

  // TODO: Errormessages to user
  // TODO: sendMessage in own method
  public Bot(String botName, DBOperator dbOperator) {

    super(System.getenv("BOT_TOKEN"));
    this.botName = botName;
    this.db = dbOperator;
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

        db.insertChats(name, Integer.parseInt(chatId));

        sendMessage.setText("Hello " + name + ", added your ID: " + chatId + " to the Brodcast");

        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      } else if (messageText.contains("add")) {
        
        String stockSymbol = messageText.toUpperCase().replaceAll("ADD", "").replace("\\", "").strip();
        db.insertStock(stockSymbol);

        sendMessage.setText("Added " + stockSymbol + " to watch list");

        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      } else if (messageText.contains("rm")) {

        String stockSymbol = messageText.toUpperCase().replaceAll("RM", "").replace("\\", "").strip();
        db.removeStock(stockSymbol);

        sendMessage.setText("Removed " + stockSymbol + " from watch list");

        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      } else if (messageText.equalsIgnoreCase("stop")) {

        db.removeChats(Integer.parseInt(chatId));

        sendMessage.setText("Removed your ID: " + chatId);

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

    return this.botName;
  }

  @Override
  public String getBotToken() {

    return System.getenv("BOT_TOKEN");
  }
}
