package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Vitaliy", "Usoltsev", (byte) 20);
        userDaoJDBC.saveUser("Evgeniy", "Usoltsev", (byte) 31);
        userDaoJDBC.saveUser("Irina", "Pamorzina", (byte) 21);
        userDaoJDBC.saveUser("Alexandr", "Feredyk", (byte) 42);

        userDaoJDBC.getAllUsers();

        userDaoJDBC.removeUserById(3);

        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.dropUsersTable();

    }
}