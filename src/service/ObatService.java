package service;

import dao.ObatDAO;

import javafx.collections.ObservableList;

import model.Obat;

public class ObatService {

    // =========================
    // DAO
    // =========================
    private ObatDAO dao =
            new ObatDAO();

    // =========================
    // GET ALL
    // =========================
    public ObservableList<Obat> getAll(){

        return dao.getData();
    }

    // =========================
    // SEARCH
    // =========================
    public ObservableList<Obat> search(
            String keyword){

        return dao.search(keyword);
    }

    // =========================
    // INSERT
    // =========================
    public void insert(Obat obat)
            throws Exception{

        // VALIDASI

        if(obat.getNamaObat() == null
                || obat.getNamaObat()
                .trim()
                .isEmpty()){

            throw new Exception(
                    "Nama obat wajib diisi");
        }

        if(obat.getStok() < 0){

            throw new Exception(
                    "Stok tidak valid");
        }

        if(obat.getHarga() < 0){

            throw new Exception(
                    "Harga tidak valid");
        }

        dao.insertObat(obat);
    }

    // =========================
    // UPDATE
    // =========================
    public void update(Obat obat)
            throws Exception{

        if(obat.getIdObat() <= 0){

            throw new Exception(
                    "ID obat tidak valid");
        }

        dao.updateObat(obat);
    }

    // =========================
    // DELETE
    // =========================
    public void delete(int id)
            throws Exception{

        if(id <= 0){

            throw new Exception(
                    "ID obat tidak valid");
        }

        dao.deleteObat(id);
    }
}