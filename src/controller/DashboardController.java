package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class DashboardController {

    @FXML
    private VBox sidebar;

    @FXML
    private Label logoTitle;

    @FXML
    private Button btnDashboard,
                btnPasien,
                btnDokter,
                btnPetugas,
                btnObat;

    private boolean collapsed = false;

    @FXML
private Label lblMaster, lblTransaksi, lblLaporan;

@FXML
private Button btnPendaftaran,
               btnPemeriksaan,
               btnRekam,
               btnPrediksi;

@FXML
private void toggleSidebar(){

    if(!collapsed){

        sidebar.setPrefWidth(80);

        logoTitle.setVisible(false);

        lblMaster.setVisible(false);
        lblTransaksi.setVisible(false);
        lblLaporan.setVisible(false);

        btnDashboard.setText("🏠");
        btnPasien.setText("👨");
        btnDokter.setText("🩺");
        btnPetugas.setText("👩");
        btnObat.setText("💊");

        btnPendaftaran.setText("📝");
        btnPemeriksaan.setText("🩻");
        btnRekam.setText("📋");
        btnPrediksi.setText("🧠");

        collapsed = true;

    }else{

        sidebar.setPrefWidth(240);

        logoTitle.setVisible(true);

        lblMaster.setVisible(true);
        lblTransaksi.setVisible(true);
        lblLaporan.setVisible(true);

        logoTitle.setText("SMART CLINIC");

        btnDashboard.setText("🏠 Dashboard");
        btnPasien.setText("👨‍⚕ Pasien");
        btnDokter.setText("🩺 Dokter");
        btnPetugas.setText("👩‍💼 Petugas");
        btnObat.setText("💊 Obat");

        btnPendaftaran.setText("📝 Pendaftaran");
        btnPemeriksaan.setText("🩻 Pemeriksaan");
        btnRekam.setText("📋 Rekam Medis");
        btnPrediksi.setText("🧠 Prediksi ML");

        collapsed = false;
    }
}

    @FXML
    private void openPasien() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/pasien.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle("Data Pasien");

            stage.setScene(
                    new Scene(root));
            /* FULL SCREEN */
            stage.setMaximized(true);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
     @FXML
    private void openDokter() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/dokter.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle("Data Dokter");

            stage.setScene(
                    new Scene(root));
            /* FULL SCREEN */
            stage.setMaximized(true);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }   
}