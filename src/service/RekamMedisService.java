package service;

import dao.RekamMedisDAO;
import javafx.collections.ObservableList;
import model.RekamMedis;

public class RekamMedisService {

    private RekamMedisDAO dao = new RekamMedisDAO();

    public ObservableList<RekamMedis> getAll() {
        return dao.getAll();
    }

    public ObservableList<RekamMedis> search(String keyword) {
        return dao.search(keyword);
    }

    public ObservableList<RekamMedis> getByPasien(int idPasien) {
        return dao.getByPasien(idPasien);
    }

    public RekamMedis getByPeriksa(int idPeriksa) {
        return dao.getByPeriksa(idPeriksa);
    }
}
