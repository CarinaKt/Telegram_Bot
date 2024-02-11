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
    // Use the method we defined earlier to create a datasource
    static DataSource dataSource = DB.createDataSource();
    static Connection connection;

    public DBOperator() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static List<String> readStocks() {
        List<String> stocksList = new ArrayList<>();
        // get a connection from the datasource
        try {
            // Create a new statement on the connection
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM stocks");

            // Execute the query, and store the results in the ResultSet instance
            ResultSet rs = stmt.executeQuery();

            // We run a loop to process the results.
            // The rs.next() method moves the result pointer to the next result row, and
            // returns
            // true if a row is present, and false otherwise
            // Note that initially the result pointer points before the first row, so we
            // have to call
            // rs.next() the first time
            while (rs.next()) {
                // Now that `rs` points to a valid row (rs.next() is true), we can use the
                // `getString`
                // and `getLong` methods to return each column value of the row as a string and
                // long
                // respectively, and print it to the console
                stocksList.add(rs.getString("stock"));
                System.out.printf("stock:%s ", rs.getString("stock"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stocksList;

    }

    public static void insertStock(String stock) {
        if (stock != null && stock != "") {
            try {

                // Create a new insert statement with the bird and description values as query
                // params
                PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO stocks(stock) VALUES(?)");

                // Set the query params
                insertStmt.setString(1, stock);

                // Run the insert query using the `executeUpdate` method.
                // This returns the number of inserted rows
                int insertedRows = insertStmt.executeUpdate();
                // Print out the number of inserted rows
                System.out.printf("inserted %s stock(s)%n", insertedRows);

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public static void removeStock(String stock) {
        if (stock != null && stock != "") {
            try {

                // Similar to the previous example, we can use query params to fill in the
                // delete condition
                PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM stocks WHERE stock = ?");
                deleteStmt.setString(1, stock);
                int deletedRows = deleteStmt.executeUpdate();
                System.out.printf("deleted %s stock(s)%n", deletedRows);

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public static Map<String, String> readChats() {
        Map<String, String> chatsMap = new HashMap<>();

        try {
            // Create a new statement on the connection
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM chats");

            // Execute the query, and store the results in the ResultSet instance
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                chatsMap.put( String.valueOf(id), rs.getString("name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return chatsMap;

    }

    public static void insertChats(String name, int id) {
        try {

            // Create a new insert statement with the bird and description values as query
            // params
            PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO chats(id, name) VALUES(?, ?)");

            // Set the query params
            insertStmt.setInt(1, id);
            insertStmt.setString(2, name);

            int insertedRows = insertStmt.executeUpdate();
            // Print out the number of inserted rows
            System.out.printf("inserted %s stock(s)%n", insertedRows);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void removeChats(int id) {

        try {

            // Similar to the previous example, we can use query params to fill in the
            // delete condition
            PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM chats WHERE id = ?");
            deleteStmt.setInt(1, id);
            int deletedRows = deleteStmt.executeUpdate();
            System.out.printf("deleted %s chats(s)%n", deletedRows);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

}
