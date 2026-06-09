package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.*;

public class UserDAO {

    Connection conn = DBConnection.connect();

    // LOGIN
    public User login(String username, String password) {
        try {
            String sql =
                    "SELECT u.id_user, u.nama, u.username, u.password, u.id_role, u.id_dokter, r.nama_role " +
                    "FROM users u " +
                    "JOIN roles r ON u.id_role = r.id_role " +
                    "WHERE u.username = ? AND u.password = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // LOAD ALL
    public ObservableList<User> getAllUsers() {
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            String sql =
                    "SELECT u.id_user, u.nama, u.username, u.password, u.id_role, u.id_dokter, r.nama_role " +
                    "FROM users u " +
                    "JOIN roles r ON u.id_role = r.id_role";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(mapResultSetToUser(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // INSERT
    public void insertUser(User user) {
        try {
            String sql =
                    "INSERT INTO users(nama, username, password, id_role, id_dokter) VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getNama());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdRole());

            if (user.getIdDokter() == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, user.getIdDokter());
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateUser(User user) {
        try {
            String sql =
                    "UPDATE users SET nama=?, username=?, password=?, id_role=?, id_dokter=? WHERE id_user=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getNama());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdRole());

            if (user.getIdDokter() == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, user.getIdDokter());
            }

            ps.setInt(6, user.getIdUser());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteUser(int id) {
        try {
            String sql = "DELETE FROM users WHERE id_user=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SEARCH
    public ObservableList<User> searchUser(String keyword) {
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            String sql =
                    "SELECT u.id_user, u.nama, u.username, u.password, u.id_role, u.id_dokter, r.nama_role " +
                    "FROM users u " +
                    "JOIN roles r ON u.id_role = r.id_role " +
                    "WHERE u.nama LIKE ? OR u.username LIKE ? OR r.nama_role LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToUser(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id_user"),
                rs.getString("nama"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("id_role"),
                rs.getString("nama_role"),
                rs.getObject("id_dokter") == null ? null : rs.getInt("id_dokter")
        );
    }
}