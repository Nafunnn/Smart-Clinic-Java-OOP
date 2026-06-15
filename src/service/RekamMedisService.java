package service;

import dao.RekamMedisDAO;
import javafx.collections.ObservableList;
import model.RekamMedis;

public class RekamMedisService {

    private RekamMedisDAO dao = new RekamMedisDAO();

    public ObservableList<RekamMedis> getAll() {
        return dao.getAllRekamMedis();
    }

    public ObservableList<RekamMedis> search(String keyword) {
        return dao.searchRekamMedis(keyword);
    }
}