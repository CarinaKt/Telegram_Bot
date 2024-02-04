package telegram_bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import telegram_bot.Objects.Chats;

public class FileOperations {

    Chats chat = new Chats();

    public void loadFile(){
        String fileName = "bot.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
                return;
            }
        }

        try (
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))
        ) {
            // Read operation
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Write operation
            writer.write("This is a new line added to the file.");
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }
    
    public void writeToFile(){

    }
    
}