package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HasilRiwayat {

    private int idRekam;
    private int idPeriksa;
    private int idPasien;
    private Date tanggal;

    private String namaPasien;
    private String namaDokter;
    private String diagnosa;
    private String ringkasan;
    private String catatan;

    private double tekananDarah;
    private double gulaDarah;
    private double suhu;
    private double beratBadan;

    private String hasilPrediksi;
    private String tingkatResiko;
    private Double probabilitas;

    public HasilRiwayat() {
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

    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
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

    public String getRingkasan() {
        return ringkasan != null ? ringkasan : "";
    }

    public void setRingkasan(String ringkasan) {
        this.ringkasan = ringkasan;
    }

    public String getCatatan() {
        return catatan != null ? catatan : "";
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public double getTekananDarah() {
        return tekananDarah;
    }

    public void setTekananDarah(double tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public double getGulaDarah() {
        return gulaDarah;
    }

    public void setGulaDarah(double gulaDarah) {
        this.gulaDarah = gulaDarah;
    }

    public double getSuhu() {
        return suhu;
    }

    public void setSuhu(double suhu) {
        this.suhu = suhu;
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(double beratBadan) {
        this.beratBadan = beratBadan;
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

    public Double getProbabilitas() {
        return probabilitas;
    }

    public void setProbabilitas(Double probabilitas) {
        this.probabilitas = probabilitas;
    }

    public String getTanggalFormatted() {
        if (tanggal == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(tanggal);
    }

    public String getProbabilitasFormatted() {
        if (probabilitas == null) {
            return "-";
        }
        return String.format("%.0f%%", probabilitas * 100);
    }
}
