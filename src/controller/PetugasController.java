package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import util.AlertUtil;
import util.SceneUtil;

public class PetugasController {

    @FXML
    private TableView<User> tablePetugas;

    @FXML
    private TableColumn<User, Integer> colId;

    @FXML
    private TableColumn<User, String> colNama, colUsername, colRole;

    @FXML
    private TextField txtCari;

    private UserService userService = new UserService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tablePetugas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        loadData();
    }

    @FXML
    public void loadData() {
        tablePetugas.setItems(userService.getAll());
    }

    @FXML
    public void handleCari() {
        tablePetugas.setItems(userService.search(txtCari.getText()));
    }

    @FXML
    public void handleTambah() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_petugas.fxml"));
            Stage stage = SceneUtil.createModal(loader, "Tambah Pengguna", 800, 420);
            FormPetugasController controller = loader.getController();
            controller.setModeTambah();
            stage.showAndWait();
            loadData();
        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form tambah pengguna");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEdit() {
        try {
            User selected = tablePetugas.getSelectionModel().getSelectedItem();
            if (selected == null) {
                AlertUtil.warning("Pilih data terlebih dahulu");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_petugas.fxml"));
            Stage stage = SceneUtil.createModal(loader, "Edit Pengguna", 800, 420);
            FormPetugasController controller = loader.getController();
            controller.setModeEdit(selected);
            stage.showAndWait();
            loadData();
        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form edit pengguna");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleHapus() {
        try {
            User selected = tablePetugas.getSelectionModel().getSelectedItem();
            if (selected == null) {
                AlertUtil.warning("Pilih data terlebih dahulu");
                return;
            }

            userService.delete(selected.getIdUser());
            AlertUtil.success("Data pengguna berhasil dihapus");
            loadData();
        } catch (Exception e) {
            AlertUtil.error("Gagal menghapus data pengguna");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) tablePetugas.getScene().getWindow();
        stage.close();
    }
}
