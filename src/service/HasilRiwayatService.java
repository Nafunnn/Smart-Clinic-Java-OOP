package service;

import dao.HasilRiwayatDAO;
import javafx.collections.ObservableList;
import model.HasilRiwayat;
import model.Pasien;

public class HasilRiwayatService {

    private HasilRiwayatDAO dao = new HasilRiwayatDAO();
    private PasienService pasienService = new PasienService();

    public ObservableList<HasilRiwayat> getTimelineByPasien(int idPasien) {
        return dao.getByPasien(idPasien);
    }

    public ObservableList<HasilRiwayat> searchTimeline(int idPasien, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getTimelineByPasien(idPasien);
        }
        return dao.searchByPasien(idPasien, keyword.trim());
    }

    public ObservableList<HasilRiwayat> getAll() {
        return dao.getAll();
    }

    public Pasien getPasienSummary(int idPasien) {
        for (Pasien pasien : pasienService.getAll()) {
            if (pasien.getIdPasien() == idPasien) {
                return pasien;
            }
        }
        return null;
    }
}
