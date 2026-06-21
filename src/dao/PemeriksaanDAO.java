package dao;

import database.DBConnection;
// import database.Koneksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Dokter;
import model.Obat;
import model.Pasien;
import model.Pemeriksaan;
import model.Pendaftaran;
import model.RekamMedis;
import model.ResepObat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class PemeriksaanDAO {

    //private Connection conn = Koneksi.getConnection();
    private Connection conn = DBConnection.connect();
    private RekamMedisDAO rekamMedisDAO = new RekamMedisDAO();
    // =========================
    // LOAD DATA PEMERIKSAAN
    // =========================
    public ObservableList<Pemeriksaan> getData() {

        ObservableList<Pemeriksaan> list =
                FXCollections.observableArrayList();

        String sql = """
                SELECT p.*,
                       df.keluhan,
                       ps.id_pasien,
                       ps.nama AS nama_pasien,
                       d.id_dokter,
                       d.nama AS nama_dokter
                FROM pemeriksaan p
                JOIN pendaftaran df
                    ON p.id_daftar = df.id_daftar
                JOIN pasien ps
                    ON df.id_pasien = ps.id_pasien
                JOIN dokter d
                    ON df.id_dokter = d.id_dokter
                """;

        try {

            PreparedStatement st =
                    conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                // pasien
                Pasien pasien = new Pasien();
                pasien.setIdPasien(
                        rs.getInt("id_pasien"));
                pasien.setNama(
                        rs.getString("nama_pasien"));

                // dokter
                Dokter dokter = new Dokter();
                dokter.setIdDokter(
                        rs.getInt("id_dokter"));
                dokter.setNama(
                        rs.getString("nama_dokter"));

                // pendaftaran
                Pendaftaran daftar =
                        new Pendaftaran();

                daftar.setIdDaftar(
                        rs.getInt("id_daftar"));

                daftar.setKeluhan(
                        rs.getString("keluhan"));

                daftar.setPasien(pasien);
                daftar.setDokter(dokter);

                // pemeriksaan
                Pemeriksaan p =
                        new Pemeriksaan();

                p.setIdPeriksa(
                        rs.getInt("id_periksa"));

                p.setPendaftaran(daftar);

                p.setTanggalPeriksa(
                        rs.getDate("tanggal_periksa"));

                p.setDiagnosa(
                        rs.getString("diagnosa"));

                p.setTekananDarah(
                        rs.getDouble("tekanan_darah"));

                p.setGulaDarah(
                        rs.getDouble("gula_darah"));

                p.setSuhu(
                        rs.getDouble("suhu"));

                p.setBeratBadan(
                        rs.getDouble("berat_badan"));

                p.setCatatan(
                        rs.getString("catatan"));

                p.setHasilPrediksi(
                        rs.getString("hasil_prediksi"));

                p.setTingkatResiko(
                        rs.getString("tingkat_resiko"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // SIMPAN PEMERIKSAAN
    // =========================
    public void insertPemeriksaan(
            Pemeriksaan p,
            ObservableList<ResepObat> listResep,
            RekamMedis rekam) {

        try {

            // =====================
            // INSERT PEMERIKSAAN
            // =====================

            String sqlPeriksa = """
                    INSERT INTO pemeriksaan
                    (
                    id_daftar,
                    tanggal_periksa,
                    diagnosa,
                    tekanan_darah,
                    gula_darah,
                    suhu,
                    berat_badan,
                    catatan,
                    hasil_prediksi,
                    tingkat_resiko
                    )
                    VALUES
                    (?,?,?,?,?,?,?,?,?,?)
                    """;

            PreparedStatement st =
                    conn.prepareStatement(
                            sqlPeriksa,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            st.setInt(1,
                    p.getPendaftaran().getIdDaftar());

            st.setDate(2,
                    new java.sql.Date(
                            p.getTanggalPeriksa().getTime()));

            st.setString(3,
                    p.getDiagnosa());

            st.setDouble(4,
                    p.getTekananDarah());

            st.setDouble(5,
                    p.getGulaDarah());

            st.setDouble(6,
                    p.getSuhu());

            st.setDouble(7,
                    p.getBeratBadan());

            st.setString(8,
                    p.getCatatan());

            st.setString(9,
                    p.getHasilPrediksi());

            st.setString(10,
                    p.getTingkatResiko());

            st.executeUpdate();

            // ambil id pemeriksaan
            ResultSet rs =
                    st.getGeneratedKeys();

            int idPeriksa = 0;

            if (rs.next()) {
                idPeriksa = rs.getInt(1);
            }

            // =====================
            // INSERT RESEP
            // =====================

            String sqlResep = """
                    INSERT INTO resep_obat
                    (
                    id_periksa,
                    id_obat,
                    jumlah,
                    dosis,
                    keterangan
                    )
                    VALUES
                    (?,?,?,?,?)
                    """;

            PreparedStatement stResep =
                    conn.prepareStatement(sqlResep);
                for (ResepObat resep : listResep) {

                stResep.setInt(1, idPeriksa);

                stResep.setInt(
                        2,
                        resep.getObat().getIdObat());

                stResep.setInt(
                        3,
                        resep.getJumlah());

                stResep.setString(
                        4,
                        resep.getDosis());

                stResep.setString(
                        5,
                        resep.getKeterangan());

                stResep.executeUpdate();
                }

        //     stResep.setInt(1, idPeriksa);

        //     stResep.setInt(2,
        //             resep.getObat().getIdObat());

        //     stResep.setInt(3,
        //             resep.getJumlah());

        //     stResep.setString(4,
        //             resep.getDosis());

        //     stResep.setString(5,
        //             resep.getKeterangan());

        //     stResep.executeUpdate();

            // =====================
            // INSERT REKAM MEDIS
            // =====================
            rekamMedisDAO.insert(idPeriksa, rekam);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // HAPUS PEMERIKSAAN
    // =========================
    public void deletePemeriksaan(int id) {

        try {

            // hapus resep
            String resep =
                    "DELETE FROM resep_obat WHERE id_periksa=?";

            PreparedStatement st1 =
                    conn.prepareStatement(resep);

            st1.setInt(1, id);
            st1.executeUpdate();

            // hapus rekam medis
            rekamMedisDAO.deleteByPeriksa(id);

            // hapus pemeriksaan
            String periksa =
                    "DELETE FROM pemeriksaan WHERE id_periksa=?";

            PreparedStatement st3 =
                    conn.prepareStatement(periksa);

            st3.setInt(1, id);
            st3.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // SEARCH
    // =========================
    public ObservableList<Pemeriksaan>  search(String keyword) {

        ObservableList<Pemeriksaan> list =
                FXCollections.observableArrayList();

        String sql = """
                SELECT p.*,
                       df.keluhan,
                       ps.id_pasien,
                       ps.nama AS nama_pasien,
                       d.id_dokter,
                       d.nama AS nama_dokter
                FROM pemeriksaan p
                JOIN pendaftaran df
                    ON p.id_daftar = df.id_daftar
                JOIN pasien ps
                    ON df.id_pasien = ps.id_pasien
                JOIN dokter d
                    ON df.id_dokter = d.id_dokter
                WHERE ps.nama LIKE ?
                   OR d.nama LIKE ?
                   OR p.diagnosa LIKE ?
                """;

        try {

            PreparedStatement st =
                    conn.prepareStatement(sql);

            st.setString(1,
                    "%" + keyword + "%");

            st.setString(2,
                    "%" + keyword + "%");

            st.setString(3,
                    "%" + keyword + "%");

            ResultSet rs =
                    st.executeQuery();

            while (rs.next()) {

                Pasien pasien = new Pasien();
                pasien.setIdPasien(
                        rs.getInt("id_pasien"));
                pasien.setNama(
                        rs.getString("nama_pasien"));

                Dokter dokter = new Dokter();
                dokter.setIdDokter(
                        rs.getInt("id_dokter"));
                dokter.setNama(
                        rs.getString("nama_dokter"));

                Pendaftaran daftar =
                        new Pendaftaran();

                daftar.setIdDaftar(
                        rs.getInt("id_daftar"));

                daftar.setKeluhan(
                        rs.getString("keluhan"));

                daftar.setPasien(pasien);
                daftar.setDokter(dokter);

                Pemeriksaan p =
                        new Pemeriksaan();

                p.setIdPeriksa(
                        rs.getInt("id_periksa"));

                p.setPendaftaran(daftar);

                p.setTanggalPeriksa(
                        rs.getDate("tanggal_periksa"));

                p.setDiagnosa(
                        rs.getString("diagnosa"));

                list.add(p);

                p.setHasilPrediksi(
                        rs.getString("hasil_prediksi"));

                p.setTingkatResiko(
                        rs.getString("tingkat_resiko"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
public ObservableList<ResepObat> getResepByPemeriksaan(
        int idPeriksa) {

    ObservableList<ResepObat> list =
            FXCollections.observableArrayList();

    String sql =
            "SELECT ro.*, o.nama_obat "
            + "FROM resep_obat ro "
            + "JOIN obat o ON ro.id_obat = o.id_obat "
            + "WHERE ro.id_periksa = ?";

    try {

        Connection conn =
                DBConnection.connect();

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setInt(1, idPeriksa);

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            Obat obat = new Obat();

            obat.setIdObat(
                    rs.getInt("id_obat"));

            obat.setNamaObat(
                    rs.getString("nama_obat"));

            ResepObat resep =
                    new ResepObat();

            resep.setIdResep(
                    rs.getInt("id_resep"));

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
public void updatePemeriksaan(
        Pemeriksaan p,
        ObservableList<ResepObat> listResep,
        RekamMedis rekam) {

    try {

        // ======================
        // UPDATE PEMERIKSAAN
        // ======================

        String sql = """
                UPDATE pemeriksaan
                SET
                id_daftar=?,
                diagnosa=?,
                tekanan_darah=?,
                gula_darah=?,
                suhu=?,
                berat_badan=?,
                catatan=?,
                hasil_prediksi=?,
                tingkat_resiko=?
                WHERE id_periksa=?
                """;

        PreparedStatement st =
                conn.prepareStatement(sql);

        st.setInt(1,
                p.getPendaftaran().getIdDaftar());

        st.setString(2,
                p.getDiagnosa());

        st.setDouble(3,
                p.getTekananDarah());

        st.setDouble(4,
                p.getGulaDarah());

        st.setDouble(5,
                p.getSuhu());

        st.setDouble(6,
                p.getBeratBadan());

        st.setString(7,
                p.getCatatan());

        st.setString(8,
                p.getHasilPrediksi());

        st.setString(9,
                p.getTingkatResiko());

        st.setInt(10,
                p.getIdPeriksa());

        st.executeUpdate();

        // ======================
        // HAPUS RESEP LAMA
        // ======================

        String deleteResep =
                "DELETE FROM resep_obat "
                + "WHERE id_periksa=?";

        PreparedStatement del =
                conn.prepareStatement(deleteResep);

        del.setInt(1,
                p.getIdPeriksa());

        del.executeUpdate();

        // ======================
        // INSERT RESEP BARU
        // ======================

        String sqlResep = """
                INSERT INTO resep_obat
                (
                id_periksa,
                id_obat,
                jumlah,
                dosis,
                keterangan
                )
                VALUES
                (?,?,?,?,?)
                """;

        PreparedStatement stResep =
                conn.prepareStatement(sqlResep);

        for (ResepObat resep : listResep) {

            stResep.setInt(
                    1,
                    p.getIdPeriksa());

            stResep.setInt(
                    2,
                    resep.getObat().getIdObat());

            stResep.setInt(
                    3,
                    resep.getJumlah());

            stResep.setString(
                    4,
                    resep.getDosis());

            stResep.setString(
                    5,
                    resep.getKeterangan());

            stResep.executeUpdate();
        }

        // ======================
        // UPDATE REKAM MEDIS
        // ======================
        rekamMedisDAO.saveOrUpdate(p.getIdPeriksa(), rekam);

    } catch (Exception e) {

        e.printStackTrace();
    }
}
public ObservableList<ResepObat> getByPemeriksaan(int idPeriksa) {

    ObservableList<ResepObat> list =FXCollections.observableArrayList();

    String sql = """
        SELECT ro.*, o.nama_obat
        FROM resep_obat ro
        JOIN obat o
        ON ro.id_obat = o.id_obat
        WHERE ro.id_periksa = ?
    """;

    try {

        Connection conn =
                DBConnection.connect();

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setInt(1, idPeriksa);

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            Obat obat = new Obat();

            obat.setIdObat(
                    rs.getInt("id_obat"));

            obat.setNamaObat(
                    rs.getString("nama_obat"));

            ResepObat r =
                    new ResepObat();

            r.setIdResep(
                    rs.getInt("id_resep"));

            r.setObat(obat);

            r.setJumlah(
                    rs.getInt("jumlah"));

            r.setDosis(
                    rs.getString("dosis"));

            r.setKeterangan(
                    rs.getString("keterangan"));

            list.add(r);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return list;
}

}