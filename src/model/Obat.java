package model;

public class Obat {

    private int idObat;
    private String namaObat;
    private int stok;
    private double harga;
    private String aturanPakai;
    private int kodeKfa;

    public Obat() {
    }

    public Obat(int idObat,
                String namaObat,
                int stok,
                double harga) {

        this.idObat = idObat;
        this.namaObat = namaObat;
        this.stok = stok;
        this.harga = harga;
    }

    public Obat(int idObat,
                String namaObat,
                int stok,
                double harga,
                String aturanPakai,
                int kodeKfa) {

        this.idObat = idObat;
        this.namaObat = namaObat;
        this.stok = stok;
        this.harga = harga;
        this.aturanPakai = aturanPakai;
        this.kodeKfa = kodeKfa;
    }

    public int getIdObat() {
        return idObat;
    }

    public void setIdObat(int idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getAturanPakai() {
        return aturanPakai;
    }

    public void setAturanPakai(String aturanPakai) {
        this.aturanPakai = aturanPakai;
    }

    public int getKodeKfa() {
        return kodeKfa;
    }

    public void setKodeKfa(int kodeKfa) {
        this.kodeKfa = kodeKfa;
    }

    @Override
    public String toString() {
        return namaObat;
    }
}
