package database;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smart_clinic"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=Asia/Jakarta";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil!");
            return conn;
        } catch (Exception e) {
            System.out.println("Koneksi gagal!");
            e.printStackTrace();
            showAlert("ERROR", e.getMessage());
            return null;
        }
    }

    private static void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
