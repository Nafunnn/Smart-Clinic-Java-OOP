package dao;

import database.DBConnection;
import model.User;

import java.sql.*;

public class UserDAO {

    Connection conn = DBConnection.connect();

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}