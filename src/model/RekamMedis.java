package model;

import java.util.Date;

public class RekamMedis {

    private int idRekam;
    private Pemeriksaan pemeriksaan;
    private Date tanggal;
    private String ringkasan;

    public RekamMedis() {
    }

    public RekamMedis(int idRekam, Pemeriksaan pemeriksaan, Date tanggal, String ringkasan) {
        this.idRekam = idRekam;
        this.pemeriksaan = pemeriksaan;
        this.tanggal = tanggal;
        this.ringkasan = ringkasan;
    }

    public int getIdRekam() {
        return idRekam;
    }

    public void setIdRekam(int idRekam) {
        this.idRekam = idRekam;
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

    // helper TableView
    public String getNamaPasien() {
        return pemeriksaan != null ? pemeriksaan.getNamaPasien() : "";
    }

    public String getNamaDokter() {
        return pemeriksaan != null ? pemeriksaan.getNamaDokter() : "";
    }

    public String getDiagnosa() {
        return pemeriksaan != null ? pemeriksaan.getDiagnosa() : "";
    }

    public String getKeluhan() {
        return pemeriksaan != null ? pemeriksaan.getKeluhan() : "";
    }
}