package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RekamMedis {

    private int idRekam;
    private int idPeriksa;
    private Pemeriksaan pemeriksaan;
    private Date tanggal;
    private String ringkasan;

    private String namaPasien;
    private String namaDokter;
    private String diagnosa;
    private String hasilPrediksi;
    private String tingkatResiko;

    public RekamMedis() {
    }

    public int getIdRekam() {
        return idRekam;
    }

    public void setIdRekam(int idRekam) {
        this.idRekam = idRekam;
    }

    public int getIdPeriksa() {
        return idPeriksa;
    }

    public void setIdPeriksa(int idPeriksa) {
        this.idPeriksa = idPeriksa;
    }

    public Pemeriksaan getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(Pemeriksaan pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getRingkasan() {
        return ringkasan;
    }

    public void setRingkasan(String ringkasan) {
        this.ringkasan = ringkasan;
    }

    public String getNamaPasien() {
        return namaPasien != null ? namaPasien : "";
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaDokter() {
        return namaDokter != null ? namaDokter : "";
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getDiagnosa() {
        return diagnosa != null ? diagnosa : "";
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getHasilPrediksi() {
        return hasilPrediksi != null ? hasilPrediksi : "";
    }

    public void setHasilPrediksi(String hasilPrediksi) {
        this.hasilPrediksi = hasilPrediksi;
    }

    public String getTingkatResiko() {
        return tingkatResiko != null ? tingkatResiko : "";
    }

    public void setTingkatResiko(String tingkatResiko) {
        this.tingkatResiko = tingkatResiko;
    }

    public String getTanggalFormatted() {
        if (tanggal == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(tanggal);
    }
}
