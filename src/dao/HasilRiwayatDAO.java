package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HasilRiwayat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HasilRiwayatDAO {

    private static final String BASE_QUERY = """
            SELECT rm.id_rekam,
                   rm.id_periksa,
                   rm.tanggal,
                   rm.ringkasan,
                   pm.diagnosa,
                   pm.catatan,
                   pm.tekanan_darah,
                   pm.gula_darah,
                   pm.suhu,
                   pm.berat_badan,
                   pm.hasil_prediksi,
                   pm.tingkat_resiko,
                   pm.probabilitas,
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

    public ObservableList<HasilRiwayat> getAll() {
        ObservableList<HasilRiwayat> list = FXCollections.observableArrayList();

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

    public ObservableList<HasilRiwayat> getByPasien(int idPasien) {
        ObservableList<HasilRiwayat> list = FXCollections.observableArrayList();

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

    public ObservableList<HasilRiwayat> searchByPasien(int idPasien, String keyword) {
        ObservableList<HasilRiwayat> list = FXCollections.observableArrayList();

        String sql = BASE_QUERY + """
                 WHERE ps.id_pasien = ?
                   AND (d.nama LIKE ?
                     OR pm.diagnosa LIKE ?
                     OR rm.ringkasan LIKE ?
                     OR pm.hasil_prediksi LIKE ?
                     OR pm.tingkat_resiko LIKE ?)
                 ORDER BY rm.tanggal DESC, rm.id_rekam DESC
                """;

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            String pattern = "%" + keyword + "%";
            st.setInt(1, idPasien);
            st.setString(2, pattern);
            st.setString(3, pattern);
            st.setString(4, pattern);
            st.setString(5, pattern);
            st.setString(6, pattern);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private HasilRiwayat mapResultSet(ResultSet rs) throws SQLException {
        HasilRiwayat item = new HasilRiwayat();
        item.setIdRekam(rs.getInt("id_rekam"));
        item.setIdPeriksa(rs.getInt("id_periksa"));
        item.setIdPasien(rs.getInt("id_pasien"));
        item.setTanggal(rs.getDate("tanggal"));
        item.setRingkasan(rs.getString("ringkasan"));
        item.setDiagnosa(rs.getString("diagnosa"));
        item.setCatatan(rs.getString("catatan"));
        item.setTekananDarah(rs.getDouble("tekanan_darah"));
        item.setGulaDarah(rs.getDouble("gula_darah"));
        item.setSuhu(rs.getDouble("suhu"));
        item.setBeratBadan(rs.getDouble("berat_badan"));
        item.setHasilPrediksi(rs.getString("hasil_prediksi"));
        item.setTingkatResiko(rs.getString("tingkat_resiko"));

        double prob = rs.getDouble("probabilitas");
        if (rs.wasNull()) {
            item.setProbabilitas(null);
        } else {
            item.setProbabilitas(prob);
        }

        item.setNamaPasien(rs.getString("nama_pasien"));
        item.setNamaDokter(rs.getString("nama_dokter"));
        return item;
    }
}
