package service;

import dao.UserDAO;
import javafx.collections.ObservableList;
import model.User;

public class UserService {

    private UserDAO dao = new UserDAO();

    public User login(String username, String password) {
        return dao.login(username, password);
    }

    public ObservableList<User> getAll() {
        return dao.getAllUsers();
    }

    public ObservableList<User> search(String keyword) {
        return dao.searchUser(keyword);
    }

    public void insert(User user) throws Exception {
        dao.insertUser(user);
    }

    public void update(User user) throws Exception {
        dao.updateUser(user);
    }

    public void delete(int id) throws Exception {
        dao.deleteUser(id);
    }
}