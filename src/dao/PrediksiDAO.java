package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prediksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrediksiDAO {

    private static final String BASE_QUERY = """
            SELECT pm.id_periksa,
                   pm.tanggal_periksa,
                   pm.hasil_prediksi,
                   pm.tingkat_resiko,
                   pm.probabilitas,
                   ps.id_pasien
            FROM pemeriksaan pm
            JOIN pendaftaran df ON pm.id_daftar = df.id_daftar
            JOIN pasien ps ON df.id_pasien = ps.id_pasien
            """;

    private Connection conn = DBConnection.connect();

    public ObservableList<Prediksi> getByPasien(int idPasien) {
        ObservableList<Prediksi> list = FXCollections.observableArrayList();

        String sql = BASE_QUERY + """
                 WHERE ps.id_pasien = ?
                   AND pm.hasil_prediksi IS NOT NULL
                   AND pm.hasil_prediksi <> ''
                 ORDER BY pm.tanggal_periksa DESC, pm.id_periksa DESC
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

    public Prediksi getByPeriksa(int idPeriksa) {
        String sql = BASE_QUERY + " WHERE pm.id_periksa = ?";

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

    private Prediksi mapResultSet(ResultSet rs) throws SQLException {
        Prediksi prediksi = new Prediksi();
        prediksi.setIdPrediksi(rs.getInt("id_periksa"));
        prediksi.setIdPeriksa(rs.getInt("id_periksa"));
        prediksi.setIdPasien(rs.getInt("id_pasien"));
        prediksi.setHasilPrediksi(rs.getString("hasil_prediksi"));
        prediksi.setTanggalPrediksi(rs.getDate("tanggal_periksa"));

        double prob = rs.getDouble("probabilitas");
        if (!rs.wasNull()) {
            prediksi.setProbabilitas(prob);
        }

        return prediksi;
    }
}
