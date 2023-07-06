package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}
    public void createUsersTable()
            throws ClassNotFoundException, SQLException {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/mySQL_DB";
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String nameDB = "Humans";
        final String USER = "admin";
        final String PASSWORD = "1812rtif";

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Creating table in selected database...");
            statement = connection.createStatement();
            String SQL =
                    "CREATE TABLE IF NOT EXISTS " + nameDB +
                            "(Id INTEGER NOT NULL AUTO_INCREMENT, " +
                            "Name VARCHAR(50), " +
                            "LastName VARCHAR(50), " +
                            "Age INTEGER NOT NULL ," +
                            "primary key (Id))";
            statement.executeUpdate(SQL);
            System.out.println("Table successfully created... \n");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void dropUsersTable()
            throws ClassNotFoundException, SQLException {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/mySQL_DB";
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String USER = "admin";
        final String PASSWORD = "1812rtif";
        final String nameDB = "Humans";

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Deleting the table...");
            String SQL =
                    "DROP TABLE IF EXISTS " + nameDB;
            statement.executeUpdate(SQL);
            System.out.println("Table successfully deleted!");
        } finally {
            if (statement != null)
                statement.close();
        }

    }
    public void saveUser(String name, String lastName, byte age)
            throws SQLException, ClassNotFoundException {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/mySQL_DB";
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String USER = "admin";
        final String PASSWORD = "1812rtif";

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Saving a user...");
            String SQL = String.format(
                    "INSERT INTO Humans(Name, LastName, Age) values ('%s', '%s', %d)", name, lastName, age);
            statement.executeUpdate(SQL);
            System.out.println("User с именем \"" + name + "\" добавлен в базу данных \n");
        } finally {
            if (statement != null)
                statement.close();
        }
    }
    public void removeUserById(long id)
            throws SQLException, ClassNotFoundException {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/mySQL_DB";
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String USER = "admin";
        final String PASSWORD = "1812rtif";

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Deleting a user...");
            String SQL = "DELETE FROM Humans WHERE id =  " + id;
            statement.executeUpdate(SQL);
            System.out.println("User ID " + id + " deleted");
        } finally {
            if (statement != null)
                statement.close();
        }
    }
    public List<User> getAllUsers()
            throws SQLException, ClassNotFoundException {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/mySQL_DB";
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String USER = "admin";
        final String PASSWORD = "1812rtif";

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Getting all users...");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Humans");
            List<User> linkedList = new LinkedList<>();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                User user = new User(name, lastName, (byte) age);
                System.out.println(user);
                linkedList.add(user);
            }
            return linkedList;
        } finally {
            if (statement != null)
                statement.close();
        }
    }
    public void cleanUsersTable()
            throws ClassNotFoundException, SQLException {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/mySQL_DB";
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String USER = "admin";
        final String PASSWORD = "1812rtif";

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Cleaning the table...");
            String SQL =
                    "DELETE FROM Humans";
            statement.executeUpdate(SQL);
            System.out.println("Table is clean!");
        } finally {
            if (statement != null)
                statement.close();
        }
    }
}
