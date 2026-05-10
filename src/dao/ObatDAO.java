package dao;

import database.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Obat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ObatDAO {

    // =========================
    // GET ALL
    // =========================
    public ObservableList<Obat> getData() {

        ObservableList<Obat> list =
                FXCollections.observableArrayList();

        String sql =
                "SELECT * FROM obat";

        try {

            Connection conn =
                    DBConnection.connect();

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while (rs.next()) {

                Obat obat =
                        new Obat();

                obat.setIdObat(
                        rs.getInt("id_obat"));

                obat.setNamaObat(
                        rs.getString("nama_obat"));

                obat.setStok(
                        rs.getInt("stok"));

                obat.setHarga(
                        rs.getDouble("harga"));

                list.add(obat);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // SEARCH
    // =========================
    public ObservableList<Obat> search(
            String keyword) {

        ObservableList<Obat> list =
                FXCollections.observableArrayList();

        String sql =
                "SELECT * FROM obat "
                + "WHERE nama_obat LIKE ?";

        try {

            Connection conn =
                    DBConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Obat obat =
                        new Obat();

                obat.setIdObat(
                        rs.getInt("id_obat"));

                obat.setNamaObat(
                        rs.getString("nama_obat"));

                obat.setStok(
                        rs.getInt("stok"));

                obat.setHarga(
                        rs.getDouble("harga"));

                list.add(obat);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // INSERT
    // =========================
    public void insertObat(
            Obat obat) {

        String sql =
                "INSERT INTO obat "
                + "(nama_obat, stok, harga) "
                + "VALUES (?, ?, ?)";

        try {

            Connection conn =
                    DBConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    obat.getNamaObat());

            ps.setInt(
                    2,
                    obat.getStok());

            ps.setDouble(
                    3,
                    obat.getHarga());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // UPDATE
    // =========================
    public void updateObat(
            Obat obat) {

        String sql =
                "UPDATE obat SET "
                + "nama_obat=?, "
                + "stok=?, "
                + "harga=? "
                + "WHERE id_obat=?";

        try {

            Connection conn =
                    DBConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    obat.getNamaObat());

            ps.setInt(
                    2,
                    obat.getStok());

            ps.setDouble(
                    3,
                    obat.getHarga());

            ps.setInt(
                    4,
                    obat.getIdObat());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // DELETE
    // =========================
    public void deleteObat(
            int id) {

        String sql =
                "DELETE FROM obat "
                + "WHERE id_obat=?";

        try {

            Connection conn =
                    DBConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}