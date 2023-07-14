package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {

    private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 5);
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return new LinkedList<>(userDaoJDBC.getAllUsers());
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }
}
