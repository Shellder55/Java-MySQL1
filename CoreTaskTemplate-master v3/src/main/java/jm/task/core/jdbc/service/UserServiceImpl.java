package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {

    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 5);
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        return userDaoJDBC.getAllUsers();
//        return null;
    }

    public void cleanUsersTable() {

    }
}
