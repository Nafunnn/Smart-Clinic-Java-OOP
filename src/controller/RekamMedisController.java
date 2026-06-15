package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.RekamMedis;
import service.RekamMedisService;

public class RekamMedisController {

    @FXML
    private TableView<RekamMedis> tableRekamMedis;

    @FXML
    private TableColumn<RekamMedis, Integer> colId;

    @FXML
    private TableColumn<RekamMedis, String> colTanggal, colPasien, colDokter, colDiagnosa, colRingkasan;

    @FXML
    private TextField txtCari;

    private RekamMedisService rekamMedisService = new RekamMedisService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idRekam"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colPasien.setCellValueFactory(new PropertyValueFactory<>("namaPasien"));
        colDokter.setCellValueFactory(new PropertyValueFactory<>("namaDokter"));
        colDiagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosa"));
        colRingkasan.setCellValueFactory(new PropertyValueFactory<>("ringkasan"));

        loadData();
    }

    private void loadData() {
        tableRekamMedis.setItems(rekamMedisService.getAll());
    }

    @FXML
    public void handleCari() {
        tableRekamMedis.setItems(rekamMedisService.search(txtCari.getText()));
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) tableRekamMedis.getScene().getWindow();
        stage.close();
    }
}