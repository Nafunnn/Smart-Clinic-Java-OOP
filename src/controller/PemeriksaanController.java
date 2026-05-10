package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Pemeriksaan;
import model.ResepObat;
import javafx.print.PrinterJob;
import javafx.scene.layout.VBox;
import util.AlertUtil;
import util.SceneUtil;
import service.PemeriksaanService;

public class PemeriksaanController {
    @FXML
    private TableView<Pemeriksaan> tablePemeriksaan;
    @FXML
    private TableColumn<Pemeriksaan, Integer> colId;
    @FXML
    private TableColumn<Pemeriksaan, String> colTanggal;
    @FXML
    private TableColumn<Pemeriksaan, String> colPasien;
    @FXML
    private TableColumn<Pemeriksaan, String> colDokter;
    @FXML
    private TableColumn<Pemeriksaan, String> colKeluhan;
    @FXML
    private TableColumn<Pemeriksaan, String> colDiagnosa;
    @FXML
    private TableColumn<Pemeriksaan, Double> colTekanan;
    @FXML
    private TableColumn<Pemeriksaan, Double> colGula;
    @FXML
    private TableColumn<Pemeriksaan, String> colPrediksi;
    @FXML
    private TableColumn<Pemeriksaan, String> colResiko;
    @FXML
    private TextField txtCari;

    // private PemeriksaanDAO dao = new PemeriksaanDAO();
    private PemeriksaanService  pemeriksaanService =new PemeriksaanService();
    
    private ObservableList<Pemeriksaan> list;
    // =========================
    // INITIALIZE
    // =========================
    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idPeriksa"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalPeriksa"));
        colPasien.setCellValueFactory(new PropertyValueFactory<>("namaPasien"));
        colDokter.setCellValueFactory(new PropertyValueFactory<>("namaDokter"));
        colKeluhan.setCellValueFactory(new PropertyValueFactory<>("keluhan"));
        colDiagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosa"));
        colTekanan.setCellValueFactory(new PropertyValueFactory<>("tekananDarah"));
        colGula.setCellValueFactory(new PropertyValueFactory<>("gulaDarah"));
        colPrediksi.setCellValueFactory(new PropertyValueFactory<>("hasilPrediksi"));
        colResiko.setCellValueFactory(new PropertyValueFactory<>("tingkatResiko"));
        loadData();
    }

    // =========================
    // LOAD DATA
    // =========================
    private void loadData() {
        list = pemeriksaanService.getAll();
        //list = dao.getData();
        tablePemeriksaan.setItems(list);
    }

    @FXML
    private void handlePrint() {
        Pemeriksaan p =tablePemeriksaan.getSelectionModel().getSelectedItem();
        if (p == null) {
             AlertUtil.warning("Pilih data pemeriksaan!");
             return;
        }
        try {
             VBox root = new VBox(10);
                root.setStyle("""
                -fx-padding: 20;
                -fx-font-size: 14;
                """);

                Label title = new Label("SMART CLINIC");
                title.setStyle("""
                -fx-font-size: 24;
                -fx-font-weight: bold;
                """);

                root.getChildren().add(title);
                root.getChildren().add(new Label("================================"));
                root.getChildren().add(new Label("Nama Pasien : "+ p.getNamaPasien()));
                root.getChildren().add(new Label("Dokter : "+ p.getNamaDokter()));
                root.getChildren().add(new Label("Tanggal : "+ p.getTanggalPeriksa()));
                root.getChildren().add(new Label("Diagnosa : "+ p.getDiagnosa()));
                root.getChildren().add(new Label("================================"));
                root.getChildren().add(new Label("RESEP OBAT"));
                root.getChildren().add(new Label("================================"));

                ObservableList<ResepObat> list =  pemeriksaanService.getResep(p.getIdPeriksa());
                int no = 1;
                for (ResepObat r : list) {
                        root.getChildren().add(new Label(no + ". "+ r.getObat().getNamaObat()));
                        root.getChildren().add(new Label("   Jumlah : "+ r.getJumlah()));
                        root.getChildren().add(new Label("   Dosis : "+ r.getDosis()));
                        root.getChildren().add(new Label("   Ket : "+ r.getKeterangan()));
                        root.getChildren().add(new Label(" "));
                no++;
                }
                PrinterJob job =PrinterJob.createPrinterJob();

                if (job != null&& job.showPrintDialog(tablePemeriksaan.getScene().getWindow())) {
                        boolean success =job.printPage(root);
                        if (success) {job.endJob();}
                }

        } catch (Exception e) {
                e.printStackTrace();
        }
     }
    // =========================
    // TAMBAH
    // =========================
    @FXML
    private void handleTambah() {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/form_pemeriksaan.fxml"));
            Stage stage =SceneUtil.createModal(loader,"Tambah Pemeriksaan",960,520);
            FormPemeriksaanController controller =loader.getController();
            stage.showAndWait();            
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // EDIT
    // =========================
    @FXML
    private void handleEdit() {
        Pemeriksaan p =tablePemeriksaan.getSelectionModel().getSelectedItem();
        if (p == null) {
            AlertUtil.warning("Pilih data terlebih dahulu!");
            return;
        }
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/form_pemeriksaan.fxml"));
            Stage stage =SceneUtil.createModal(loader,"Tambah Pemeriksaan",960,520);
            FormPemeriksaanController controller =loader.getController();
            controller.setEditData(p);
            stage.showAndWait();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // HAPUS
    // =========================
    @FXML
    private void handleHapus() {

        try{
            Pemeriksaan p =tablePemeriksaan.getSelectionModel().getSelectedItem();
            if (p == null) {
                AlertUtil.warning("Pilih data terlebih dahulu!");
                return;
            }
            boolean confirm =AlertUtil.confirm("Hapus data ini?");
            if (confirm) {
                pemeriksaanService.delete(p.getIdPeriksa());
                loadData();
                AlertUtil.success("Data berhasil dihapus");
            }
        }catch(Exception e){
            AlertUtil.error("Gagal menghapus data");
            e.printStackTrace();
        }
    }

    // =========================
    // CARI
    // =========================
    @FXML
    private void handleCari() {

        String keyword =txtCari.getText();
        tablePemeriksaan.setItems(pemeriksaanService.search(keyword));
    }

    // =========================
    // REFRESH
    // =========================
    @FXML
    private void handleRefresh() {
        txtCari.clear();
        loadData();
    }

    // =========================
    // CLOSE
    // =========================
    @FXML
    private void handleClose() {
        Stage stage =(Stage) tablePemeriksaan.getScene().getWindow();
        stage.close();
    }
}