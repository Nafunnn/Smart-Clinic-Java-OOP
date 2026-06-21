package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import util.AlertUtil;
import util.ValidationUtil;

public class FormPetugasController {

    @FXML
    private TextField txtNama, txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> cmbRole;

    private UserService userService = new UserService();

    private boolean isEdit = false;
    private int idUser = 0;
    private Integer idDokter = null;

    @FXML
    public void initialize() {
        cmbRole.getItems().addAll("Admin", "Petugas", "Dokter");
        cmbRole.setValue("Petugas");
    }

    public void setModeTambah() {
        isEdit = false;
        idUser = 0;
        idDokter = null;
        cmbRole.setValue("Petugas");
    }

    public void setModeEdit(User user) {
        isEdit = true;
        idUser = user.getIdUser();
        idDokter = user.getIdDokter();

        txtNama.setText(user.getNama());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        cmbRole.setValue(user.getRole());
    }

    @FXML
    public void handleSimpan() {
        try {
            if (ValidationUtil.isEmpty(txtNama, "Nama")) return;
            if (ValidationUtil.isEmpty(txtUsername, "Username")) return;
            if (ValidationUtil.isEmpty(txtPassword, "Password")) return;

            int idRole = getIdRoleFromName(cmbRole.getValue());

            User user = new User(
                    idUser,
                    txtNama.getText(),
                    txtUsername.getText(),
                    txtPassword.getText(),
                    idRole,
                    cmbRole.getValue(),
                    idDokter
            );

            userService.simpan(user, isEdit);
            AlertUtil.success(isEdit ? "Data pengguna berhasil diupdate" : "Data pengguna berhasil disimpan");
            closeWindow();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBatal() {
        closeWindow();
    }

    private int getIdRoleFromName(String role) {
        if ("Admin".equals(role)) return 1;
        if ("Petugas".equals(role)) return 2;
        if ("Dokter".equals(role)) return 3;
        return 2;
    }

    private void closeWindow() {
        Stage stage = (Stage) txtNama.getScene().getWindow();
        stage.close();
    }
}
