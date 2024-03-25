package telegram_bot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class DBOperator {

    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    
    static DataSource dataSource = DB.createDataSource();
    static Connection connection;

    public DBOperator() {
        try {
            connection = dataSource.getConnection();

        } catch (SQLException e) {
            logger.info("Connection failed: " + e);
            e.printStackTrace();
        }
    }

    public List<String> readStocks() {
        List<String> stocksList = new ArrayList<>();
        // get a connection from the datasource
        try {
            // Create a new statement on the connection
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM stocks");

            // Execute the query, and store the results in the ResultSet instance
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stocksList.add(rs.getString("stock"));

                logger.info("stock: " + rs.getString("stock"));
            }
        } catch (SQLException e) {
            logger.info("Connection failed: " + e);
            e.printStackTrace();
        }

        return stocksList;

    }

    public void insertStock(String stock) {

        if (stock != null && stock != "") {

            try {
                PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO stocks(stock) VALUES(?)");

                insertStmt.setString(1, stock);

                int insertedRows = insertStmt.executeUpdate();

                logger.info("inserted " + insertedRows + "Rows");

            } catch (SQLException e) {
                logger.info("Connection failed: " + e);
                System.err.println(e.getMessage());
            }
        }

    }

    public void removeStock(String stock) {

        if (stock != null && stock != "") {

            try {
                PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM stocks WHERE stock = ?");
                deleteStmt.setString(1, stock);
                int deletedRows = deleteStmt.executeUpdate();

                logger.info("deleted " + deletedRows + " rows");

            } catch (SQLException e) {
                logger.info("Connection failed: " + e);
                System.err.println(e.getMessage());
            }
        }

    }

    public Map<String, String> readChats() {
        Map<String, String> chatsMap = new HashMap<>();

        try {

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM chats");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                chatsMap.put(String.valueOf(id), rs.getString("name"));
            }
        } catch (SQLException e) {
            logger.info("Connection failed: " + e);
            e.printStackTrace();
        }
        return chatsMap;

    }

    public void insertChats(String name, int id) {
        try {

            PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO chats(id, name) VALUES(?, ?)");

            insertStmt.setInt(1, id);
            insertStmt.setString(2, name);
            int insertedRows = insertStmt.executeUpdate();

            logger.info("inserted " + insertedRows + " rows");

        } catch (SQLException e) {
            logger.info("Connection failed: " + e);
            System.err.println(e.getMessage());
        }

    }

    public void removeChats(int id) {

        try {

            PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM chats WHERE id = ?");
            deleteStmt.setInt(1, id);
            int deletedRows = deleteStmt.executeUpdate();

            logger.info("deleted " + deletedRows + " rows");

        } catch (SQLException e) {
            logger.info("Failed: " + e);
            System.err.println(e.getMessage());
        }

    }

}
