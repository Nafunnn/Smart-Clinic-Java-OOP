package controller;

import database.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Pasien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import dao.PasienDAO;

public class FormPasienController {

    @FXML
    private TextField txtId,
            txtNama,
            txtUmur,
            txtAlamat,
            txtHP,
            txtTekanan,
            txtGula;

    @FXML
    private ComboBox<String> cbGender;

//     Connection conn = DBConnection.connect();
    PasienDAO pasienDAO = new PasienDAO();

    private boolean modeEdit = false;

    @FXML
    public void initialize(){

        cbGender.getItems().addAll(
                "Laki-laki",
                "Perempuan"
        );
    }

    public void setModeTambah(){

        modeEdit = false;
    }

    public void setModeEdit(Pasien p){

        modeEdit = true;

        txtId.setText(
                String.valueOf(p.getIdPasien()));

        txtNama.setText(
                p.getNama());

        txtUmur.setText(
                String.valueOf(p.getUmur()));

        cbGender.setValue(
                p.getGender());

        txtAlamat.setText(
                p.getAlamat());

        txtHP.setText(
                p.getNoHP());

        txtTekanan.setText(
                String.valueOf(
                        p.getTekananDarah()));

        txtGula.setText(
                String.valueOf(
                        p.getGulaDarah()));
    }

    @FXML
        public void handleSimpan(){

        try{

                Pasien p = new Pasien(
                        modeEdit ?
                                Integer.parseInt(txtId.getText())
                                : 0,

                        txtNama.getText(),

                        Integer.parseInt(txtUmur.getText()),

                        cbGender.getValue(),

                        txtAlamat.getText(),

                        txtHP.getText(),

                        Double.parseDouble(txtTekanan.getText()),

                        Double.parseDouble(txtGula.getText())
                );

                if(modeEdit){

                pasienDAO.updatePasien(p);

                }else{

                pasienDAO.insertPasien(p);
                }

                closeWindow();

        }catch(Exception e){

                e.printStackTrace();
        }
        }

    @FXML
    public void handleBatal(){

        closeWindow();
    }

    private void closeWindow(){

        Stage stage =
                (Stage) txtNama
                .getScene()
                .getWindow();

        stage.close();
    }
}