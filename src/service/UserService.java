package service;

import dao.UserDAO;
import model.User;

public class UserService {

    private UserDAO dao = new UserDAO();

    public User login(String username, String password) {

        return dao.login(username, password);
    }
}