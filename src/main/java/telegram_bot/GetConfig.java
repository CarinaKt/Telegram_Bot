package telegram_bot;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class GetConfig {

    public String getBotToken() {

        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "config.properties";

        try {

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);

            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            inputStream.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return prop.getProperty("API_KEY");
    }

    public String getApiToken() {

        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "config.properties";

        try {

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);

            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            inputStream.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return prop.getProperty("BOT_TOKEN");
    }
    
}
