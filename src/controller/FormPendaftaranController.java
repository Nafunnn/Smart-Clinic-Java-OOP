package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Dokter;
import model.Pasien;
import model.Pendaftaran;
import service.DokterService;
import service.PasienService;
import service.PendaftaranService;
import util.AlertUtil;
import util.ValidationUtil;
import java.sql.Date;


public class FormPendaftaranController {

    @FXML
    private TextArea txtKeluhan;

    @FXML
    private DatePicker dpTanggal;

    @FXML
    private ComboBox<Pasien> cbPasien;

    @FXML
    private ComboBox<Dokter> cbDokter;

    private PendaftaranService pendaftaranService =new PendaftaranService();
    private PasienService pasienService =new PasienService();
    private DokterService dokterService =new DokterService();

    private boolean editMode = false;

    private int idEdit;

    @FXML
    public void initialize(){
         cbPasien.setItems(FXCollections.observableArrayList(pasienService.getAll()));
         cbDokter.setItems(FXCollections.observableArrayList(dokterService.getAll()));
    }
    @FXML
    public void handleSimpan(){
        try{
           if(ValidationUtil.isEmpty(dpTanggal,"Tanggal wajib diisi")){
                return;
           }
           Date tanggal =
           Date.valueOf(dpTanggal.getValue());
           Pendaftaran p =new Pendaftaran(idEdit,tanggal,txtKeluhan.getText(),cbPasien.getValue(),cbDokter.getValue());
           pendaftaranService.simpan(p, editMode);
           AlertUtil.success( "Data berhasil disimpan");
           closeForm();
        }catch(Exception e){
                AlertUtil.error(e.getMessage());
        }
    }

    @FXML
    public void handleBatal(){
        closeForm();
    }
    public void setModeTambah(){
        editMode = false;
    }

    public void setEditData(Pendaftaran p) {
        editMode = true;
        idEdit = p.getIdDaftar();
        txtKeluhan.setText(p.getKeluhan());
        cbPasien.setValue(p.getPasien());
        cbDokter.setValue(p.getDokter());
        dpTanggal.setValue(((java.sql.Date) p.getTanggal()).toLocalDate()
        );
    }

    private void closeForm(){
        Stage stage =(Stage) txtKeluhan.getScene().getWindow();
        stage.close();
    }


}