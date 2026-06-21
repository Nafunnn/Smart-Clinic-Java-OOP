package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RekamMedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RekamMedisDAO {

    private static final String BASE_QUERY = """
            SELECT rm.*,
                   pm.diagnosa,
                   pm.hasil_prediksi,
                   pm.tingkat_resiko,
                   ps.id_pasien,
                   ps.nama AS nama_pasien,
                   d.nama AS nama_dokter
            FROM rekam_medis rm
            JOIN pemeriksaan pm ON rm.id_periksa = pm.id_periksa
            JOIN pendaftaran df ON pm.id_daftar = df.id_daftar
            JOIN pasien ps ON df.id_pasien = ps.id_pasien
            JOIN dokter d ON df.id_dokter = d.id_dokter
            """;

    private Connection conn = DBConnection.connect();

    public ObservableList<RekamMedis> getAll() {
        ObservableList<RekamMedis> list = FXCollections.observableArrayList();

        String sql = BASE_QUERY + " ORDER BY rm.tanggal DESC, rm.id_rekam DESC";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<RekamMedis> search(String keyword) {
        ObservableList<RekamMedis> list = FXCollections.observableArrayList();

        String sql = BASE_QUERY + """
                 WHERE ps.nama LIKE ?
                    OR d.nama LIKE ?
                    OR rm.ringkasan LIKE ?
                    OR pm.diagnosa LIKE ?
                 ORDER BY rm.tanggal DESC, rm.id_rekam DESC
                """;

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            String pattern = "%" + keyword + "%";
            st.setString(1, pattern);
            st.setString(2, pattern);
            st.setString(3, pattern);
            st.setString(4, pattern);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<RekamMedis> getByPasien(int idPasien) {
        ObservableList<RekamMedis> list = FXCollections.observableArrayList();

        String sql = BASE_QUERY + """
                 WHERE ps.id_pasien = ?
                 ORDER BY rm.tanggal DESC, rm.id_rekam DESC
                """;

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idPasien);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public RekamMedis getByPeriksa(int idPeriksa) {
        String sql = BASE_QUERY + " WHERE rm.id_periksa = ?";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idPeriksa);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insert(int idPeriksa, RekamMedis rekam) {
        String sql = """
                INSERT INTO rekam_medis (id_periksa, tanggal, ringkasan)
                VALUES (?, ?, ?)
                """;

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idPeriksa);
            st.setDate(2, new java.sql.Date(rekam.getTanggal().getTime()));
            st.setString(3, rekam.getRingkasan());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int idPeriksa, RekamMedis rekam) {
        String sql = """
                UPDATE rekam_medis
                SET tanggal = ?, ringkasan = ?
                WHERE id_periksa = ?
                """;

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(rekam.getTanggal().getTime()));
            st.setString(2, rekam.getRingkasan());
            st.setInt(3, idPeriksa);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOrUpdate(int idPeriksa, RekamMedis rekam) {
        if (getByPeriksa(idPeriksa) != null) {
            update(idPeriksa, rekam);
        } else {
            insert(idPeriksa, rekam);
        }
    }

    public void deleteByPeriksa(int idPeriksa) {
        String sql = "DELETE FROM rekam_medis WHERE id_periksa = ?";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idPeriksa);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RekamMedis mapResultSet(ResultSet rs) throws SQLException {
        RekamMedis rekam = new RekamMedis();
        rekam.setIdRekam(rs.getInt("id_rekam"));
        rekam.setIdPeriksa(rs.getInt("id_periksa"));
        rekam.setTanggal(rs.getDate("tanggal"));
        rekam.setRingkasan(rs.getString("ringkasan"));
        rekam.setDiagnosa(rs.getString("diagnosa"));
        rekam.setHasilPrediksi(rs.getString("hasil_prediksi"));
        rekam.setTingkatResiko(rs.getString("tingkat_resiko"));
        rekam.setNamaPasien(rs.getString("nama_pasien"));
        rekam.setNamaDokter(rs.getString("nama_dokter"));
        return rekam;
    }
}
