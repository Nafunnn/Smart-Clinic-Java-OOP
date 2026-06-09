package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import util.AlertUtil;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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

        loadData();
    }

    private void loadData() {
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
            Parent root = loader.load();

            FormPetugasController controller = loader.getController();
            controller.setModeTambah();

            Stage stage = new Stage();
            stage.setTitle("Tambah Petugas");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadData();

        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form tambah petugas");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEdit() {
        try {
            User selected = tablePetugas.getSelectionModel().getSelectedItem();

            if (selected == null) {
                AlertUtil.error("Pilih data petugas terlebih dahulu");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_petugas.fxml"));
            Parent root = loader.load();

            FormPetugasController controller = loader.getController();
            controller.setModeEdit(selected);

            Stage stage = new Stage();
            stage.setTitle("Edit Petugas");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadData();

        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form edit petugas");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleHapus() {
        try {
            User selected = tablePetugas.getSelectionModel().getSelectedItem();

            if (selected == null) {
                AlertUtil.error("Pilih data petugas terlebih dahulu");
                return;
            }

            userService.delete(selected.getIdUser());
            AlertUtil.success("Data petugas berhasil dihapus");
            loadData();

        } catch (Exception e) {
            AlertUtil.error("Gagal menghapus data petugas");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) tablePetugas.getScene().getWindow();
        stage.close();
    }
}