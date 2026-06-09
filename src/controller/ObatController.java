package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Obat;
import service.ObatService;
import util.AlertUtil;
import util.SceneUtil;

public class ObatController {

    @FXML
    private TextField txtCari;

    @FXML
    private TableView<Obat> tableObat;

    @FXML
    private TableColumn<Obat, Integer> colId;

    @FXML
    private TableColumn<Obat, String> colNama;

    @FXML
    private TableColumn<Obat, Integer> colStok;

    @FXML
    private TableColumn<Obat, Double> colHarga;

    @FXML
    private TableColumn<Obat, String> colAturanPakai;

    @FXML
    private TableColumn<Obat, Integer> colKodeKfa;

    ObservableList<Obat> list =
            FXCollections.observableArrayList();

    private ObatService obatService = new ObatService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idObat"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaObat"));
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        colAturanPakai.setCellValueFactory(new PropertyValueFactory<>("aturanPakai"));
        colKodeKfa.setCellValueFactory(new PropertyValueFactory<>("kodeKfa"));
        tableObat.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();
    }

    @FXML
    public void loadData() {
        list.clear();
        list = obatService.getAll();
        tableObat.setItems(list);
    }

    @FXML
    public void handleTambah() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_obat.fxml"));
            Stage stage = SceneUtil.createModal(loader, "Tambah Obat", 800, 520);
            FormObatController controller = loader.getController();
            controller.setModeTambah();
            stage.showAndWait();
            loadData();
        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEdit() {
        Obat obat = tableObat.getSelectionModel().getSelectedItem();
        if (obat == null) {
            AlertUtil.warning("Pilih data obat dahulu!");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_obat.fxml"));
            Stage stage = SceneUtil.createModal(loader, "Edit Obat", 800, 520);
            FormObatController controller = loader.getController();
            controller.setModeEdit(obat);
            stage.showAndWait();
            loadData();
        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form edit");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleHapus() {
        try {
            Obat obat = tableObat.getSelectionModel().getSelectedItem();
            if (obat == null) {
                AlertUtil.warning("Pilih data obat dahulu!");
                return;
            }
            boolean confirm = AlertUtil.confirm(
                    "Yakin ingin menghapus obat ini? Resep terkait juga akan terhapus.");
            if (!confirm) {
                return;
            }
            obatService.delete(obat.getIdObat());
            AlertUtil.success("Data berhasil dihapus");
            loadData();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCari() {
        list.clear();
        list = obatService.search(txtCari.getText());
        tableObat.setItems(list);
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) tableObat.getScene().getWindow();
        stage.close();
    }
}
