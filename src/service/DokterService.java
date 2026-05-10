package service;

import dao.DokterDAO;
import javafx.collections.ObservableList;
import model.Dokter;

public class DokterService {

    private DokterDAO dao =new DokterDAO();

    // =========================
    // GET ALL
    // =========================
    public ObservableList<Dokter> getAll() {

        return dao.getAllDokter();
    }

    // =========================
    // SEARCH
    // =========================
    public ObservableList<Dokter> search(String keyword) {
        return dao.searchDokter(keyword);
    }

    // =========================
    // DELETE
    // =========================
    public void delete(int id)  throws Exception {
        dao.deleteDokter(id);
    }
    // =========================
    // INSERT
    // =========================
    public void insert(Dokter dokter)  throws Exception {
        dao.insertDokter(dokter);
    }

    // =========================
    // UPDATE
    // =========================
    public void update(Dokter dokter)  throws Exception {
        dao.updateDokter(dokter);
    }
}