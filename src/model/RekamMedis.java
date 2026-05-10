package model;

public class RekamMedis {

    private int idRekam;
    private String diagnosa;
    private String resep;
    private String catatan;

    // RELASI OBJECT
    private Pasien pasien;
    private Dokter dokter;

    public RekamMedis(int idRekam,
                      String diagnosa,
                      String resep,
                      String catatan,
                      Pasien pasien,
                      Dokter dokter) {

        this.idRekam = idRekam;
        this.diagnosa = diagnosa;
        this.resep = resep;
        this.catatan = catatan;

        this.pasien = pasien;
        this.dokter = dokter;
    }

    public void simpanRekamMedis() {

        System.out.println(
                "Rekam medis disimpan");
    }

    public void tampilRiwayat() {

        System.out.println(
                "Diagnosa : " + diagnosa);
    }
}
