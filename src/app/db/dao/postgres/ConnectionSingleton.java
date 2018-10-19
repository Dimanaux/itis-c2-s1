package app.db.dao.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionSingleton {
    private static Connection connection;

    private ConnectionSingleton() {
    }

    private static void init() throws SQLException, ClassNotFoundException {
        // bootstrap Driver class
        Class.forName("org.postgresql.Driver");

        // instance an instance
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/foodster",
                "postgres",
                "postgres"
        );
    }

    static Connection getInstance() {
        if (connection == null) {
            try {
                init();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("No driver found.", e);
            } catch (SQLException e) {
                throw new RuntimeException("Cannot establish connection.", e);
            }
        }
        return connection;
    }
}
