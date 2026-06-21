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

    public void simpan(User user, boolean modeEdit) throws Exception {
        if (user.getNama() == null || user.getNama().trim().isEmpty()) {
            throw new Exception("Nama wajib diisi");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new Exception("Username wajib diisi");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new Exception("Password wajib diisi");
        }

        if (modeEdit) {
            dao.updateUser(user);
        } else {
            dao.insertUser(user);
        }
    }

    public void insert(User user) throws Exception {
        simpan(user, false);
    }

    public void update(User user) throws Exception {
        simpan(user, true);
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID pengguna tidak valid");
        }
        dao.deleteUser(id);
    }
}
