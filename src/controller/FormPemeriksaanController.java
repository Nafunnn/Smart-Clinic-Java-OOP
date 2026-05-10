package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import util.AlertUtil;
import service.PemeriksaanService;
import service.PendaftaranService;
import service.ObatService;

public class FormPemeriksaanController {
        @FXML
        private TableView<ResepObat> tableResep;

        @FXML
        private TableColumn<ResepObat, String> colObat;

        @FXML
        private TableColumn<ResepObat, Integer> colJumlah;

        @FXML
        private TableColumn<ResepObat, String> colDosis;

        @FXML
        private TableColumn<ResepObat, String> colKeterangan;

        private ObservableList<ResepObat> listResep = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Pendaftaran> cbPendaftaran;

    @FXML
    private TextField txtTekanan;

    @FXML
    private TextField txtGula;

    @FXML
    private TextField txtSuhu;

    @FXML
    private TextField txtBerat;

    @FXML
    private TextArea txtDiagnosa;

    @FXML
    private TextField txtPrediksi;

    @FXML
    private ComboBox<String> cbResiko;

    @FXML
    private TextArea txtCatatan;

    @FXML
    private ComboBox<Obat> cbObat;

    @FXML
    private TextField txtJumlah;

    @FXML
    private TextField txtDosis;

    @FXML
    private TextArea txtKeterangan;

    // private PemeriksaanDAO dao =  new PemeriksaanDAO();
    private PemeriksaanService pemeriksaanService =new PemeriksaanService();
    private PendaftaranService pendaftaranService =new PendaftaranService();
    private ObatService  obatService =   new ObatService();

    private boolean editMode = false;
    private Pemeriksaan pemeriksaanEdit;

    // =========================
    // INITIALIZE
    // =========================
    @FXML
    public void initialize() {

       // private ObservableList<ResepObat> listResep =FXCollections.observableArrayList();
        colObat.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getObat().getNamaObat()));
        colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        colDosis.setCellValueFactory(new PropertyValueFactory<>("dosis"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        tableResep.setItems(listResep);
        loadPendaftaran();
        loadObat();
        cbResiko.getItems().addAll("Rendah","Sedang","Tinggi");

        // tampil nama pasien di combo
        cbPendaftaran.setCellFactory(param ->
                new ListCell<>() {
                    @Override
                    protected void updateItem(
                            Pendaftaran item,
                            boolean empty) {

                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(
                                    item.getNamaPasien()
                                            + " - "
                                            + item.getNamaDokter());
                        }
                    }
                });

        cbPendaftaran.setButtonCell(cbPendaftaran.getCellFactory().call(null));
        // tampil nama obat
        cbObat.setCellFactory(param ->
                new ListCell<>() {
                    @Override
                    protected void updateItem(
                            Obat item,
                            boolean empty) {

                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getNamaObat());
                        }
                    }
                });

        cbObat.setButtonCell(
                cbObat.getCellFactory().call(null));
    }


        @FXML
        private void handleTambahObat() {
        Obat obat =cbObat.getValue();
        if (obat == null) {
                AlertUtil.warning("Pilih obat!");
                return;
        }

        ResepObat resep =new ResepObat();
        resep.setObat(obat);
        resep.setJumlah(Integer.parseInt(txtJumlah.getText()));
        resep.setDosis(txtDosis.getText());
        resep.setKeterangan(txtKeterangan.getText());
        listResep.add(resep);
        tableResep.setItems(listResep);
        // reset form
        cbObat.setValue(null);
        txtJumlah.clear();
        txtDosis.clear();
        txtKeterangan.clear();
        }
    private void loadPendaftaran() {
        cbPendaftaran.setItems(pendaftaranService.getAll());
    }

    private void loadObat() {
        cbObat.setItems(obatService.getAll());
    }

    @FXML
    private void handleSimpan() {
        try {
            Pemeriksaan p =new Pemeriksaan();
            p.setPendaftaran(cbPendaftaran.getValue());
            p.setTanggalPeriksa(new Date());
            p.setDiagnosa(txtDiagnosa.getText());
            p.setTekananDarah(Double.parseDouble(txtTekanan.getText()));
            p.setGulaDarah(Double.parseDouble(txtGula.getText()));
            p.setSuhu(Double.parseDouble(txtSuhu.getText()));
            p.setBeratBadan(Double.parseDouble(txtBerat.getText()));
            p.setCatatan(txtCatatan.getText());
            p.setHasilPrediksi(txtPrediksi.getText());
            p.setTingkatResiko(cbResiko.getValue());

            // MODE EDIT
            if(editMode){
                p.setIdPeriksa(pemeriksaanEdit.getIdPeriksa());
            }

            // REKAM MEDIS
            RekamMedis rekam =new RekamMedis();
            rekam.setTanggal(new Date());
            rekam.setRingkasan(txtDiagnosa.getText()+ "\n"+ txtCatatan.getText());
            // SERVICE
            pemeriksaanService.simpan(p,listResep,rekam,editMode);
            AlertUtil.success(editMode? "Data berhasil diupdate": "Data berhasil disimpan");
            closeForm();

        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
            e.printStackTrace();
        }
    }
    // =========================
    // EDIT MODE
    // =========================
    public void setEditData(Pemeriksaan p) {
        editMode = true;
        pemeriksaanEdit = p;
        cbPendaftaran.setValue(p.getPendaftaran());
        txtTekanan.setText(String.valueOf(p.getTekananDarah()));
        txtGula.setText(String.valueOf(p.getGulaDarah()));
        txtSuhu.setText(String.valueOf(p.getSuhu()));
        txtBerat.setText(String.valueOf(p.getBeratBadan()));
        txtDiagnosa.setText(p.getDiagnosa());
        txtPrediksi.setText(p.getHasilPrediksi());
        cbResiko.setValue(p.getTingkatResiko());
        txtCatatan.setText(p.getCatatan());
        // listResep =dao.getResepByPemeriksaan(p.getIdPeriksa());
        listResep =pemeriksaanService.getResepByPemeriksaan(p.getIdPeriksa());
        tableResep.setItems(listResep);
    }
    @FXML
    private void handleHapusObat() {
        ResepObat selected =tableResep.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.warning("Pilih obat yang ingin dihapus!");
            return;
        }
        listResep.remove(selected);
     }

    // =========================
    // BATAL
    // =========================
    @FXML
    private void handleBatal() {
        closeForm();
    }

    // =========================
    // CLOSE
    // =========================
    private void closeForm() {
        Stage stage =(Stage) cbPendaftaran.getScene().getWindow();
        stage.close();
    }

}