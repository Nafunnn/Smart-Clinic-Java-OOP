package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import util.AlertUtil;
import util.ValidationUtil;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private UserService userService = new UserService();

    @FXML
    public void handleLogin() {
        try {
            if (ValidationUtil.isEmpty(txtUsername, "Username")) return;
            if (ValidationUtil.isEmpty(txtPassword, "Password")) return;

            User user = userService.login(
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            if (user != null) {
                AlertUtil.success("Login berhasil sebagai " + user.getRole());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("/style/global.css").toExternalForm());

                Stage stage = (Stage) txtUsername.getScene().getWindow();
                stage.setTitle("Smart Clinic");
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();

            } else {
                AlertUtil.error("Username atau password salah");
            }

        } catch (Exception e) {
            AlertUtil.error("Gagal login");
            e.printStackTrace();
        }
    }
}