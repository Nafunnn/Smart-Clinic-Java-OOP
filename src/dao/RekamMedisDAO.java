package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pemeriksaan;
import model.Pendaftaran;
import model.RekamMedis;
import model.Pasien;
import model.Dokter;

import java.sql.*;

public class RekamMedisDAO {

    Connection conn = DBConnection.connect();

    public ObservableList<RekamMedis> getAllRekamMedis() {
        ObservableList<RekamMedis> list = FXCollections.observableArrayList();

        try {
            String sql =
                    "SELECT rm.id_rekam, rm.tanggal, rm.ringkasan, " +
                    "p.id_periksa, p.diagnosa, " +
                    "pd.id_daftar, pd.keluhan, " +
                    "ps.nama AS nama_pasien, " +
                    "d.nama AS nama_dokter " +
                    "FROM rekam_medis rm " +
                    "JOIN pemeriksaan p ON rm.id_periksa = p.id_periksa " +
                    "JOIN pendaftaran pd ON p.id_daftar = pd.id_daftar " +
                    "JOIN pasien ps ON pd.id_pasien = ps.id_pasien " +
                    "JOIN dokter d ON pd.id_dokter = d.id_dokter";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(mapResultSetToRekamMedis(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<RekamMedis> searchRekamMedis(String keyword) {
        ObservableList<RekamMedis> list = FXCollections.observableArrayList();

        try {
            String sql =
                    "SELECT rm.id_rekam, rm.tanggal, rm.ringkasan, " +
                    "p.id_periksa, p.diagnosa, " +
                    "pd.id_daftar, pd.keluhan, " +
                    "ps.nama AS nama_pasien, " +
                    "d.nama AS nama_dokter " +
                    "FROM rekam_medis rm " +
                    "JOIN pemeriksaan p ON rm.id_periksa = p.id_periksa " +
                    "JOIN pendaftaran pd ON p.id_daftar = pd.id_daftar " +
                    "JOIN pasien ps ON pd.id_pasien = ps.id_pasien " +
                    "JOIN dokter d ON pd.id_dokter = d.id_dokter " +
                    "WHERE ps.nama LIKE ? OR d.nama LIKE ? OR p.diagnosa LIKE ? OR rm.ringkasan LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToRekamMedis(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private RekamMedis mapResultSetToRekamMedis(ResultSet rs) throws SQLException {
        Pasien pasien = new Pasien();
        pasien.setNama(rs.getString("nama_pasien"));

        Dokter dokter = new Dokter();
        dokter.setNama(rs.getString("nama_dokter"));

        Pendaftaran pendaftaran = new Pendaftaran();
        pendaftaran.setIdDaftar(rs.getInt("id_daftar"));
        pendaftaran.setKeluhan(rs.getString("keluhan"));
        pendaftaran.setPasien(pasien);
        pendaftaran.setDokter(dokter);

        Pemeriksaan pemeriksaan = new Pemeriksaan();
        pemeriksaan.setIdPeriksa(rs.getInt("id_periksa"));
        pemeriksaan.setDiagnosa(rs.getString("diagnosa"));
        pemeriksaan.setPendaftaran(pendaftaran);

        return new RekamMedis(
                rs.getInt("id_rekam"),
                pemeriksaan,
                rs.getDate("tanggal"),
                rs.getString("ringkasan")
        );
    }
}