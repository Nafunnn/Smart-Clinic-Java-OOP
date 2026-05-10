package controller;

import dao.DokterDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Dokter;
import service.DokterService;
import util.AlertUtil;
import util.ValidationUtil;
import service.DokterService;

public class FormDokterController {

    private boolean isEdit = false;
    private int idDokter;
    
    @FXML
    private TextField txtId,txtNama,txtSpesialis,txtHP;

    //DokterDAO dokterDAO =   new DokterDAO();
    private DokterService dokterService = new DokterService();
    private boolean modeEdit = false;

    public void setModeTambah(){
        modeEdit = false;
    }

    public void setModeEdit(Dokter d){
        isEdit = true;
        idDokter = d.getIdDokter();
        txtNama.setText(d.getNama());
        txtSpesialis.setText(d.getSpesialis());
        txtHP.setText(d.getNoHP());
    }
    @FXML
    public void handleSimpan(){
        try{
            // VALIDASI
            if(ValidationUtil.isEmpty(txtNama,"Nama dokter")) return;
            if(ValidationUtil.isEmpty(txtSpesialis,"Spesialis")) return;
            if(ValidationUtil.isEmpty(txtHP,"No HP")) return;
            Dokter d =new Dokter(idDokter,txtNama.getText(),txtSpesialis.getText(),txtHP.getText());
            if(isEdit){
                dokterService.update(d);
                AlertUtil.success("Data dokter berhasil diupdate");
            }else{
                dokterService.insert(d);
                AlertUtil.success("Data dokter berhasil disimpan");
            }
            closeWindow();
        }catch(Exception e){
            AlertUtil.error("Gagal menyimpan data");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBatal(){
        closeWindow();
    }
    private void closeWindow(){
        Stage stage =(Stage) txtNama.getScene().getWindow();
        stage.close();
    }
}