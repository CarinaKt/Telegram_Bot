package telegram_bot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class AlphaVantage {
    InputStream inputStream;
    Properties prop = new Properties();
    String propFileName = "config.properties";


    public AlphaVantage(){
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            inputStream.close();
        }catch(Exception e){
            System.out.println("Exception: " + e);
        } 
    }

    public String kurs(String symbol ) {
        String price = "";
    
        try {
            String apiKey = prop.getProperty("API_KEY");
            // Example: MBG.DEX = Mercedes Benz Group AG XETRA
            // ETG = Envitec
            // String symbol = "ETG"; // MSCI World ETF symbol
            String function = "GLOBAL_QUOTE";
            String urlString = "https://www.alphavantage.co/query?" +
                    "function=" + function + "&" +
                    "symbol=" + symbol + ".DEX&" +
                    "apikey=" + apiKey;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = br.readLine()) != null){
                if (line.contains("05. price")){
                    price = line;
                }
            }
            
            price = price.replaceAll("[\"\\,]", "").replace("05.", "");
            price = symbol + " " + price + "€";
            
            connection.disconnect();

            System.out.println(price);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return price;
    }
}