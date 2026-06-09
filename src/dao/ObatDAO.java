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

    private Obat mapRow(ResultSet rs) throws Exception {
        Obat obat = new Obat();
        obat.setIdObat(rs.getInt("id_obat"));
        obat.setNamaObat(rs.getString("nama_obat"));
        obat.setStok(rs.getInt("stok"));
        obat.setHarga(rs.getDouble("harga"));
        obat.setAturanPakai(rs.getString("aturan_pakai"));
        obat.setKodeKfa(rs.getInt("kode_kfa"));
        return obat;
    }

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
                list.add(mapRow(rs));
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
                list.add(mapRow(rs));
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
                + "(nama_obat, stok, harga, aturan_pakai, kode_kfa) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn =
                    DBConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, obat.getNamaObat());
            ps.setInt(2, obat.getStok());
            ps.setDouble(3, obat.getHarga());
            ps.setString(4, obat.getAturanPakai());
            ps.setInt(5, obat.getKodeKfa());

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
                + "harga=?, "
                + "aturan_pakai=?, "
                + "kode_kfa=? "
                + "WHERE id_obat=?";

        try {

            Connection conn =
                    DBConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, obat.getNamaObat());
            ps.setInt(2, obat.getStok());
            ps.setDouble(3, obat.getHarga());
            ps.setString(4, obat.getAturanPakai());
            ps.setInt(5, obat.getKodeKfa());
            ps.setInt(6, obat.getIdObat());

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
