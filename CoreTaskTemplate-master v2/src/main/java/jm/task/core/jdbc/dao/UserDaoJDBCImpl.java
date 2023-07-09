package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.awt.peer.CanvasPeer;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() throws SQLException {
    }

    Util util = new Util();
    Connection connection = Util.getConnection();
    Statement statement = Util.getStatement();
    String nameDB = Util.nameDB;

    public void createUsersTable() {
        try {
            System.out.println("Creating table in selected database...");
//            statement = connection.createStatement();
            String SQL =
                    "CREATE TABLE IF NOT EXISTS " + nameDB +
                            "(Id INTEGER NOT NULL AUTO_INCREMENT, " +
                            "Name VARCHAR(50), " +
                            "LastName VARCHAR(50), " +
                            "Age INTEGER NOT NULL ," +
                            "primary key (Id))";
            statement.executeUpdate(SQL);
            System.out.println("Table successfully created... \n");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement = connection.createStatement();
            System.out.println("Deleting the table...");
            String SQL =
                    "DROP TABLE IF EXISTS " + nameDB;
            statement.executeUpdate(SQL);
            System.out.println("Table successfully deleted!");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
//            statement = connection.createStatement();
            System.out.println("Saving a user...");
            String SQL = String.format(
                    "INSERT INTO Humans(Name, LastName, Age) values ('%s', '%s', %d)", name, lastName, age);
            statement.executeUpdate(SQL);
            System.out.println("User с именем \"" + name + "\" добавлен в базу данных \n");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public void removeUserById(long id) {
        try {
            statement = connection.createStatement();
            System.out.println("Deleting a user...");
            String SQL = "DELETE FROM Humans WHERE id =  " + id;
            statement.executeUpdate(SQL);
            System.out.println("User ID " + id + " deleted");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> linkedList = new LinkedList<>();
        try {
            statement = connection.createStatement();
            System.out.println("Getting all users...");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Humans");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                User user = new User(name, lastName, (byte) age);
                System.out.println(user);
                linkedList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
        return linkedList;
    }

    public void cleanUsersTable() {
        try {
            statement = connection.createStatement();
            System.out.println("Cleaning the table...");
            String SQL =
                    "DELETE FROM Humans";
            statement.executeUpdate(SQL);
            System.out.println("Table is clean!");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
    }
}
