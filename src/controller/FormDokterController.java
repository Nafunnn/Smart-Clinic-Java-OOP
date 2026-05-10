package controller;

import dao.DokterDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Dokter;

public class FormDokterController {
    private boolean isEdit = false;

    private int idDokter;
    @FXML
    private TextField txtId,
            txtNama,
            txtSpesialis,
            txtHP;

    DokterDAO dokterDAO =
            new DokterDAO();

    private boolean modeEdit = false;

    public void setModeTambah(){

        modeEdit = false;
    }
// public void setModeEdit(Dokter d){

//     isEdit = true;

//     idDokter = d.getIdDokter();

//     txtNama.setText(d.getNama());
//     txtSpesialis.setText(d.getSpesialis());
//     txtHP.setText(d.getNoHP());
//}
 public void setModeEdit(Dokter d){

    isEdit = true;

    idDokter = d.getIdDokter();

    txtNama.setText(d.getNama());
    txtSpesialis.setText(d.getSpesialis());
    txtHP.setText(d.getNoHP());
}
    @FXML
    public void handleSimpan(){

        Dokter d =
                new Dokter(
                        idDokter,
                        txtNama.getText(),
                        txtSpesialis.getText(),
                        txtHP.getText()
                );

        if(isEdit){

            dokterDAO.updateDokter(d);

        }else{

            dokterDAO.insertDokter(d);
        }

        closeWindow();
    }
    // @FXML
    // public void handleSimpan(){

    //     try{

    //         Dokter d =
    //                 new Dokter(

    //                         modeEdit ?
    //                                 Integer.parseInt(txtId.getText())
    //                                 : 0,

    //                         txtNama.getText(),

    //                         txtSpesialis.getText(),

    //                         txtHP.getText()
    //                 );

    //         if(modeEdit){

    //             dokterDAO.updateDokter(d);

    //         }else{

    //             dokterDAO.insertDokter(d);
    //         }

    //         closeWindow();

    //     }catch(Exception e){

    //         e.printStackTrace();
    //     }
    // }

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