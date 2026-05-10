package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Obat;
import model.ResepObat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResepObatDAO {

    public ObservableList<ResepObat> getByPemeriksaan(int idPeriksa) {

        ObservableList<ResepObat> list =
                FXCollections.observableArrayList();

        String sql =
                "SELECT ro.*, o.nama_obat " +
                "FROM resep_obat ro " +
                "JOIN obat o ON ro.id_obat = o.id_obat " +
                "WHERE ro.id_periksa=?";

        try {

            Connection conn = DBConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, idPeriksa);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ResepObat resep = new ResepObat();

                resep.setIdResep(
                        rs.getInt("id_resep"));

                Obat obat = new Obat();

                obat.setIdObat(
                        rs.getInt("id_obat"));

                obat.setNamaObat(
                        rs.getString("nama_obat"));

                resep.setObat(obat);

                resep.setJumlah(
                        rs.getInt("jumlah"));

                resep.setDosis(
                        rs.getString("dosis"));

                resep.setKeterangan(
                        rs.getString("keterangan"));

                list.add(resep);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}