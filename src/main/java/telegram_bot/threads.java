package telegram_bot;

import java.util.Timer;
import java.util.TimerTask;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class threads {

    public static void main(String[] args) throws InterruptedException{
        // Timer timer = new Timer();
        // TimerTask task = new TimerTask() {
        //     public void run() {
        //         System.out.println("Task executed");
        //     }
        // };
        // timer.schedule(task, 5000);
    

        // Timer timer = new Timer();

        // timer.schedule( new TimerTask() {
        //     public void run() {
        //         System.out.println("Task executed");
        //     }
        // }, 0, 1000);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                    // Bot bot = new Bot("Börsi");
                    // botsApi.registerBot(bot);
                    // bot.sendMessage("Hello", "37428458");
                    
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }            
             }}
             );
     
      t1.start();
     
      Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                    // Bot bot = new Bot("Börsi");
                    // botsApi.registerBot(bot);
                    // String price = AlphaVantage.kurs("ETG");
                    // bot.sendMessage("ja", "37428458");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
         });
     t2.start();
     
     t1.join();
     t2.join();
    
    }
}
