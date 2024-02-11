package telegram_bot;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class DB {
                
    static DataSource createDataSource() {
        // Get database credentials from DatabaseConfig class
        String jdbcUrl = DatabaseConfig.getDbUrl();
        String user = DatabaseConfig.getDbUsername();
        String password = DatabaseConfig.getDbPassword();

        final String url = jdbcUrl + "?user=" + user + "&password=" + password;
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }
}
