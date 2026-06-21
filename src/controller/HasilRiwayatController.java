package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.HasilRiwayat;
import model.Pasien;
import service.HasilRiwayatService;
import service.PasienService;
import util.AlertUtil;

public class HasilRiwayatController {

    @FXML
    private TableView<HasilRiwayat> tableTimeline;

    @FXML
    private TableColumn<HasilRiwayat, String> colTanggal;

    @FXML
    private TableColumn<HasilRiwayat, String> colDokter;

    @FXML
    private TableColumn<HasilRiwayat, String> colDiagnosa;

    @FXML
    private TableColumn<HasilRiwayat, String> colPrediksi;

    @FXML
    private TableColumn<HasilRiwayat, String> colProbabilitas;

    @FXML
    private TableColumn<HasilRiwayat, String> colResiko;

    @FXML
    private TableColumn<HasilRiwayat, Double> colGula;

    @FXML
    private TableColumn<HasilRiwayat, Double> colTekanan;

    @FXML
    private TableColumn<HasilRiwayat, Double> colSuhu;

    @FXML
    private TableColumn<HasilRiwayat, String> colRingkasan;

    @FXML
    private ComboBox<Pasien> cbPasien;

    @FXML
    private TextField txtCari;

    @FXML
    private Label lblInfoPasien;

    @FXML
    private TextArea txtDetail;

    private HasilRiwayatService hasilRiwayatService = new HasilRiwayatService();
    private PasienService pasienService = new PasienService();

    @FXML
    public void initialize() {
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalFormatted"));
        colDokter.setCellValueFactory(new PropertyValueFactory<>("namaDokter"));
        colDiagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosa"));
        colPrediksi.setCellValueFactory(new PropertyValueFactory<>("hasilPrediksi"));
        colProbabilitas.setCellValueFactory(new PropertyValueFactory<>("probabilitasFormatted"));
        colResiko.setCellValueFactory(new PropertyValueFactory<>("tingkatResiko"));
        colGula.setCellValueFactory(new PropertyValueFactory<>("gulaDarah"));
        colTekanan.setCellValueFactory(new PropertyValueFactory<>("tekananDarah"));
        colSuhu.setCellValueFactory(new PropertyValueFactory<>("suhu"));
        colRingkasan.setCellValueFactory(new PropertyValueFactory<>("ringkasan"));

        cbPasien.setItems(pasienService.getAll());
        cbPasien.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pasien item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNama());
            }
        });
        cbPasien.setButtonCell(cbPasien.getCellFactory().call(null));

        tableTimeline.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableTimeline.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateDetailPanel(newVal)
        );

        tableTimeline.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDetail();
            }
        });

        lblInfoPasien.setText("Pilih pasien untuk melihat riwayat dan hasil prediksi.");
        txtDetail.setEditable(false);
        txtDetail.setPromptText("Klik baris kunjungan untuk melihat detail lengkap.");
    }

    @FXML
    public void handlePilihPasien() {
        Pasien pasien = cbPasien.getValue();
        if (pasien == null) {
            tableTimeline.setItems(null);
            lblInfoPasien.setText("Pilih pasien untuk melihat riwayat dan hasil prediksi.");
            txtDetail.clear();
            return;
        }

        updatePasienHeader(pasien);
        tableTimeline.setItems(hasilRiwayatService.getTimelineByPasien(pasien.getIdPasien()));
        txtDetail.clear();
    }

    @FXML
    public void handleCari() {
        Pasien pasien = cbPasien.getValue();
        if (pasien == null) {
            AlertUtil.warning("Pilih pasien terlebih dahulu");
            return;
        }

        tableTimeline.setItems(
                hasilRiwayatService.searchTimeline(pasien.getIdPasien(), txtCari.getText())
        );
    }

    @FXML
    public void loadData() {
        txtCari.clear();
        handlePilihPasien();
    }

    @FXML
    public void handleDetail() {
        HasilRiwayat selected = tableTimeline.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.warning("Pilih kunjungan terlebih dahulu");
            return;
        }

        String detail = """
                Pasien       : %s
                Dokter       : %s
                Tanggal      : %s
                Diagnosa     : %s
                Hasil Prediksi: %s
                Probabilitas : %s
                Tingkat Risiko: %s

                Vitals Kunjungan:
                  Gula Darah   : %.0f mg/dL
                  Tekanan Darah: %.0f mmHg
                  Suhu         : %.1f °C
                  Berat Badan  : %.1f kg

                Catatan:
                %s

                Ringkasan Rekam Medis:
                %s
                """.formatted(
                selected.getNamaPasien(),
                selected.getNamaDokter(),
                selected.getTanggalFormatted(),
                selected.getDiagnosa(),
                selected.getHasilPrediksi().isEmpty() ? "-" : selected.getHasilPrediksi(),
                selected.getProbabilitasFormatted(),
                selected.getTingkatResiko().isEmpty() ? "-" : selected.getTingkatResiko(),
                selected.getGulaDarah(),
                selected.getTekananDarah(),
                selected.getSuhu(),
                selected.getBeratBadan(),
                selected.getCatatan() != null && !selected.getCatatan().isEmpty()
                        ? selected.getCatatan() : "-",
                selected.getRingkasan() != null && !selected.getRingkasan().isEmpty()
                        ? selected.getRingkasan() : "-"
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detail Hasil & Riwayat");
        alert.setHeaderText("Kunjungan #" + selected.getIdPeriksa());
        alert.setContentText(detail);
        alert.showAndWait();
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) tableTimeline.getScene().getWindow();
        stage.close();
    }

    private void updatePasienHeader(Pasien pasien) {
        Pasien summary = hasilRiwayatService.getPasienSummary(pasien.getIdPasien());
        if (summary == null) {
            lblInfoPasien.setText("Data pasien tidak ditemukan.");
            return;
        }

        lblInfoPasien.setText(String.format(
                "%s | Umur: %d | %s | Gula baseline: %.0f | Tekanan baseline: %.0f",
                summary.getNama(),
                summary.getUmur(),
                summary.getGender(),
                summary.getGulaDarah(),
                summary.getTekananDarah()
        ));
    }

    private void updateDetailPanel(HasilRiwayat item) {
        if (item == null) {
            txtDetail.clear();
            return;
        }

        txtDetail.setText(String.format(
                """
                Tanggal: %s | Dokter: %s
                Diagnosa: %s
                Prediksi: %s (%s) | Risiko: %s
                Vitals: Gula %.0f | Tekanan %.0f | Suhu %.1f | BB %.1f kg

                Catatan:
                %s

                Ringkasan:
                %s
                """,
                item.getTanggalFormatted(),
                item.getNamaDokter(),
                item.getDiagnosa(),
                item.getHasilPrediksi().isEmpty() ? "-" : item.getHasilPrediksi(),
                item.getProbabilitasFormatted(),
                item.getTingkatResiko().isEmpty() ? "-" : item.getTingkatResiko(),
                item.getGulaDarah(),
                item.getTekananDarah(),
                item.getSuhu(),
                item.getBeratBadan(),
                item.getCatatan() != null && !item.getCatatan().isEmpty() ? item.getCatatan() : "-",
                item.getRingkasan() != null && !item.getRingkasan().isEmpty() ? item.getRingkasan() : "-"
        ));
    }
}
