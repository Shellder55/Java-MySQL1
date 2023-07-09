package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/mySQL_DB?serverTimezone=UTC";
    private static final String hostName = "localhost";
    private static final String userName = "admin";
    private static final String password = "1812rtif";
    private static final String timeZone = "?serverTimezone=UTC";
    public static final String nameDB = "Humans";

    public Connection connection = DriverManager.getConnection(databaseUrl, userName, password);
    public Statement statement = connection.createStatement();

    public Util() throws SQLException {
    }

    public static Connection getMySQLConnection()
            throws SQLException {
        return getMySQLConnection(hostName, nameDB, userName, password, timeZone);
    }

    public static Connection getMySQLConnection(String hostName, String nameDB, String userName, String password, String timeZone)
            throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + nameDB + timeZone;
        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
