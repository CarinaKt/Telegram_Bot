package telegram_bot;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegram_bot.Objects.Chats;
import telegram_bot.Objects.Stocks;

public class Main {

    public static void main(String[] args) {
        
        Chats chats = new Chats();
        Stocks stocks = new Stocks();
        AlphaVantage alphaVantage = new AlphaVantage();
        Bot bot = new Bot("Börsi", chats, stocks);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(59);
        // if(now.compareTo(nextRun) > 0){
        //     nextRun = nextRun.plusDays(1);
        // }

        Duration duration = Duration.between(now, nextRun);
        long initialDelay = duration.getSeconds();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);            
        scheduler.scheduleAtFixedRate(new Runnable(){
            public void run(){
                try {
                    String price = alphaVantage.kurs("ETG");
                    bot.sendMessage(price, "37428458");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
        initialDelay,
        TimeUnit.DAYS.toSeconds(1),
        TimeUnit.SECONDS);
    }
}
