package telegram_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.LocalTime;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Timer;;

public class test extends TelegramLongPollingBot {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private String botName;
    
    public test(String botToken, String botName) {
        super(botToken);
        this.botName = botName;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Chat chat = message.getChat();
        String text = message.getText();

        if (text.equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chat.getId().toString());
            sendMessage.setText("Hello! This is my Telegram bot.");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "MyTelegramBot";
    }

    @Override
    public String getBotToken() {
        return "YOUR_BOT_TOKEN";
    }

    public void runBot(){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            // botsApi.registerBot(new Bot("Börsi"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void main(String[] args) {
        
    // TimerTask task = new TimerTask() {
    //     public void run() {
    //         System.out.println("Task performed on: " + new Date() + "n" +
    //             "Thread's name: " + Thread.currentThread().getName());
    //     }
    // };
    // Timer timer = new Timer("Timer");
    
    // long delay = 1000L;
    // timer.schedule(task, delay);
    // runBot();
    }

}