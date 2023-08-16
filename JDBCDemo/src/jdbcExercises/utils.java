package jdbcExercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static jdbcExercises.Constants.*;

enum utils {
    ;

    static final Connection getSQLConnection() throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty(USER_KEY, USER_VALUE);
        properties.setProperty(PASSWORD_KEY, PASSWORD_VALUE);
        return DriverManager.
                getConnection(JDBC_URL, properties);


    }
}
