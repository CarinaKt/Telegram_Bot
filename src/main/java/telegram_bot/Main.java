package telegram_bot;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) {
             
        AlphaVantage alphaVantage = new AlphaVantage();
        DBOperator bd = new DBOperator();
        Bot bot = new Bot("Börsi", bd);

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
                    List<String> stockList = DBOperator.readStocks();
                    Map<String, String>  chatList = DBOperator.readChats();

                    String sendMessage= "";
                    if (stockList.size() == 0) {
                        sendMessage = "No Stocks in List";
                    }

                    for(String stock : stockList){
                        sendMessage += alphaVantage.kurs(stock) + "\n";
                    }

                    for(Entry<String, String> chat : chatList.entrySet()){
                        System.out.println("chat: "+ chat);
                        System.out.println("chatID: "+ chat.getKey());
                        bot.sendMessage(sendMessage, chat.getKey().toString());
                    }
                   
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
