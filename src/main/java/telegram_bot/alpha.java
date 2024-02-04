package telegram_bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class alpha {
    public static void main(String[] args) {
        try {
            String apiKey = "07ZKURJTRHH9K2K9"; // Replace with your Alpha Vantage API key
            String symbol = "MSWI"; // MSCI World ETF symbol
            String function = "GLOBAL_QUOTE";
            String urlString = "https://www.alphavantage.co/query?" +
                    "function=" + function + "&" +
                    "symbol=" + symbol + "&" +
                    "apikey=" + apiKey;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            StringBuilder response = new StringBuilder();

            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            connection.disconnect();

            // Process the JSON response
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}