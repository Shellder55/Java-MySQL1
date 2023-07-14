package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.AddResult;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private Connection connection = null;
    private Statement statement = null;

    public void createUsersTable() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            System.out.println("Creating table in selected database...");
            String SQL =
                    "CREATE TABLE IF NOT EXISTS " + Util.nameDB +
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
            connection = Util.getConnection();
            statement = connection.createStatement();
            System.out.println("Deleting the table...");
            String SQL =
                    "DROP TABLE IF EXISTS " + Util.nameDB;
            statement.executeUpdate(SQL);
            System.out.println("Table successfully deleted!");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection = Util.getConnection();
            System.out.println("Saving a user...");
            String SQL = "INSERT INTO Humans(Name, LastName, Age) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            int resultSet = preparedStatement.executeUpdate();
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
            connection = Util.getConnection();
            System.out.println("Deleting a user...");
            String SQL = "DELETE FROM Humans WHERE id =  ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("User ID " + id + " deleted");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new LinkedList<>();
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            String SQL =
                    "SELECT * FROM Humans";
            System.out.println("Getting all users...");
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                User user = new User(name, lastName, (byte) age);
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            System.out.println("Cleaning the table...");
            String SQL =
                    "TRUNCATE TABLE Humans";
            statement.executeUpdate(SQL);
            System.out.println("Table is clean!");
        } catch (SQLException e) {
            System.out.println("Problem...");
            e.printStackTrace();
        }
    }
}
