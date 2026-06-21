package model;

import java.util.Date;

public class Prediksi implements Predictable {

    private int idPrediksi;
    private int idPeriksa;
    private int idPasien;
    private String hasilPrediksi;
    private double probabilitas;
    private Date tanggalPrediksi;

    private Pasien pasien;

    public Prediksi() {
    }

    public Prediksi(int idPrediksi, Pasien pasien) {
        this.idPrediksi = idPrediksi;
        this.pasien = pasien;
        if (pasien != null) {
            this.idPasien = pasien.getIdPasien();
        }
    }

    @Override
    public void prosesPrediksi() {
        if (pasien == null) {
            return;
        }

        double gula = pasien.getGulaDarah();

        if (gula > 200) {
            hasilPrediksi = "Risiko Diabetes Tinggi";
            probabilitas = 0.92;
        } else {
            hasilPrediksi = "Risiko Normal";
            probabilitas = 0.20;
        }
    }

    public void tampilHasil() {
        System.out.println("Hasil Prediksi : " + hasilPrediksi);
        System.out.println("Probabilitas : " + probabilitas);
    }

    public int getIdPrediksi() {
        return idPrediksi;
    }

    public void setIdPrediksi(int idPrediksi) {
        this.idPrediksi = idPrediksi;
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

    public String getHasilPrediksi() {
        return hasilPrediksi != null ? hasilPrediksi : "";
    }

    public void setHasilPrediksi(String hasilPrediksi) {
        this.hasilPrediksi = hasilPrediksi;
    }

    public double getProbabilitas() {
        return probabilitas;
    }

    public void setProbabilitas(double probabilitas) {
        this.probabilitas = probabilitas;
    }

    public Date getTanggalPrediksi() {
        return tanggalPrediksi;
    }

    public void setTanggalPrediksi(Date tanggalPrediksi) {
        this.tanggalPrediksi = tanggalPrediksi;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
        if (pasien != null) {
            this.idPasien = pasien.getIdPasien();
        }
    }
}
