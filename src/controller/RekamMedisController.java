package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Pasien;
import model.RekamMedis;
import service.PasienService;
import service.RekamMedisService;
import util.AlertUtil;

public class RekamMedisController {

    @FXML
    private TableView<RekamMedis> tableRekam;

    @FXML
    private TableColumn<RekamMedis, Integer> colId;

    @FXML
    private TableColumn<RekamMedis, String> colTanggal;

    @FXML
    private TableColumn<RekamMedis, String> colPasien;

    @FXML
    private TableColumn<RekamMedis, String> colDokter;

    @FXML
    private TableColumn<RekamMedis, String> colDiagnosa;

    @FXML
    private TableColumn<RekamMedis, String> colRingkasan;

    @FXML
    private TableColumn<RekamMedis, String> colPrediksi;

    @FXML
    private TableColumn<RekamMedis, String> colResiko;

    @FXML
    private TextField txtCari;

    @FXML
    private ComboBox<Pasien> cbPasien;

    private RekamMedisService rekamMedisService = new RekamMedisService();
    private PasienService pasienService = new PasienService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idRekam"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalFormatted"));
        colPasien.setCellValueFactory(new PropertyValueFactory<>("namaPasien"));
        colDokter.setCellValueFactory(new PropertyValueFactory<>("namaDokter"));
        colDiagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosa"));
        colRingkasan.setCellValueFactory(new PropertyValueFactory<>("ringkasan"));
        colPrediksi.setCellValueFactory(new PropertyValueFactory<>("hasilPrediksi"));
        colResiko.setCellValueFactory(new PropertyValueFactory<>("tingkatResiko"));

        cbPasien.setItems(pasienService.getAll());
        cbPasien.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pasien item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNama());
            }
        });
        cbPasien.setButtonCell(cbPasien.getCellFactory().call(null));

        tableRekam.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableRekam.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDetail();
            }
        });

        loadData();
    }

    @FXML
    public void loadData() {
        cbPasien.setValue(null);
        tableRekam.setItems(rekamMedisService.getAll());
    }

    @FXML
    public void handleCari() {
        tableRekam.setItems(rekamMedisService.search(txtCari.getText()));
    }

    @FXML
    public void handleFilterPasien() {
        Pasien pasien = cbPasien.getValue();
        if (pasien == null) {
            loadData();
            return;
        }
        tableRekam.setItems(rekamMedisService.getByPasien(pasien.getIdPasien()));
    }

    @FXML
    public void handleDetail() {
        RekamMedis selected = tableRekam.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.warning("Pilih rekam medis terlebih dahulu");
            return;
        }

        String detail = """
                Pasien    : %s
                Dokter    : %s
                Tanggal   : %s
                Diagnosa  : %s
                Prediksi  : %s
                Risiko    : %s

                Ringkasan:
                %s
                """.formatted(
                selected.getNamaPasien(),
                selected.getNamaDokter(),
                selected.getTanggalFormatted(),
                selected.getDiagnosa(),
                selected.getHasilPrediksi(),
                selected.getTingkatResiko(),
                selected.getRingkasan() != null ? selected.getRingkasan() : "-"
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detail Rekam Medis");
        alert.setHeaderText("Rekam Medis #" + selected.getIdRekam());
        alert.setContentText(detail);
        alert.showAndWait();
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) tableRekam.getScene().getWindow();
        stage.close();
    }
}
