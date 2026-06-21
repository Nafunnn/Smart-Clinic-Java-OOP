package service;

import dao.PrediksiDAO;
import javafx.collections.ObservableList;
import model.Pasien;
import model.Pemeriksaan;
import model.Prediksi;

/**
 * Service prediksi kesehatan.
 *
 * TODO fase ML: panggil dari FormPemeriksaanController.handleSimpan()
 * Prediksi result = prediksiService.predict(p, pasien);
 * p.setHasilPrediksi(result.getHasilPrediksi());
 * p.setProbabilitas(result.getProbabilitas());
 * p.setTingkatResiko(deriveTingkatResiko(result));
 *
 * Fase 2: delegasi predict() ke MLService (Python/Scikit-learn).
 */
public class PrediksiService {

    private PrediksiDAO dao = new PrediksiDAO();

    public Prediksi predict(Pemeriksaan pemeriksaan, Pasien pasien) {
        Pasien pasienForPrediksi = new Pasien(
                pasien.getIdPasien(),
                pasien.getNama(),
                pasien.getUmur(),
                pasien.getGender(),
                pasien.getAlamat(),
                pasien.getNoHP(),
                pemeriksaan.getTekananDarah() > 0
                        ? pemeriksaan.getTekananDarah()
                        : pasien.getTekananDarah(),
                pemeriksaan.getGulaDarah() > 0
                        ? pemeriksaan.getGulaDarah()
                        : pasien.getGulaDarah()
        );

        Prediksi prediksi = new Prediksi(pemeriksaan.getIdPeriksa(), pasienForPrediksi);
        prediksi.setIdPeriksa(pemeriksaan.getIdPeriksa());
        prediksi.setIdPasien(pasien.getIdPasien());
        prediksi.setTanggalPrediksi(pemeriksaan.getTanggalPeriksa());
        prediksi.prosesPrediksi();

        return prediksi;
    }

    public ObservableList<Prediksi> getByPasien(int idPasien) {
        return dao.getByPasien(idPasien);
    }

    public Prediksi getByPeriksa(int idPeriksa) {
        return dao.getByPeriksa(idPeriksa);
    }
}
