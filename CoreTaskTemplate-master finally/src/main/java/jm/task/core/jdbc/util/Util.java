package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/mySQL_DB?serverTimezone=UTC";
    private static final String userName = "admin";
    private static final String password = "1812rtif";
    public static final String nameDB = "Humans";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, userName, password);
    }
}
