package database;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;

public class DBConnection2  {
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/smart_clinic",
                "root",
                ""
            );
            System.out.println("Koneksi berhasil!");
            return conn;

        } catch (Exception e) {
            System.out.println("Koneksi gagal!");
            showAlert("Test","Koneksi Gagal");
            e.printStackTrace();
            return null;
        }
    }
    private static void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    } 
}

