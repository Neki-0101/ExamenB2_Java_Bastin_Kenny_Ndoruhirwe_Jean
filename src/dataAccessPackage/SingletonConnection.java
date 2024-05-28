package dataAccessPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection uniqueConnection;

    private SingletonConnection() {
    }

    public static Connection getInstance() {
        if (uniqueConnection == null) {
            synchronized (SingletonConnection.class) {
                if (uniqueConnection == null) {
                    try {
                        String jdbcUrl = "jdbc:mysql://localhost:3306/uni";
                        String username = "root";
                        String password = "root";
                        DriverManager.setLoginTimeout(15);
                        uniqueConnection = DriverManager.getConnection(jdbcUrl, username, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return uniqueConnection;
    }

    public static void destroyInstance() {
        uniqueConnection = null;
    }
}
