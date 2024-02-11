package telegram_bot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import telegram_bot.Objects.Chats;
import telegram_bot.Objects.Stocks;

public class FileOperations {

    Chats chat = new Chats();
	Stocks stock = new Stocks();

    public void writeChats(Chats chats){
        try {
			// write object to file
			FileOutputStream fos = new FileOutputStream("chatIDs.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(chats);
			oos.close();
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public Chats readChats(){

        Chats result = new Chats();
        try {
			// read object from file
			FileInputStream fis = new FileInputStream("chatIDs.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			result = (Chats) ois.readObject();
			ois.close();
        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return result;
    }

	public Stocks readStocks(){

		Stocks result = new Stocks();
        try {
			// read object from file
			FileInputStream fis = new FileInputStream("stocks.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			result = (Stocks) ois.readObject();
			ois.close();
        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return result;
	}

	public void writeStocks(Stocks stocks){
		try {
			// write object to file
			FileOutputStream fos = new FileOutputStream("stocks.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(stocks);
			oos.close();
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}