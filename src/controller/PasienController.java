package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import database.DBConnection;
import model.Pasien;

import java.sql.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import dao.PasienDAO;

public class PasienController {

    @FXML
    private TextField txtId,
            txtCari;

    @FXML
    private TableView<Pasien> tablePasien;

    @FXML
    private TableColumn<Pasien,Integer> colId;

    @FXML
    private TableColumn<Pasien,String> colNama;

    @FXML
    private TableColumn<Pasien,Integer> colUmur;

    @FXML
    private TableColumn<Pasien,String> colGender;

    @FXML
    private TableColumn<Pasien,String> colAlamat;

    @FXML
    private TableColumn<Pasien,String> colHP;

    @FXML
    private TableColumn<Pasien,Double> colTekanan;

    @FXML
    private TableColumn<Pasien,Double> colGula;

    ObservableList<Pasien> list =
            FXCollections.observableArrayList();

    //Connection conn ;
    //Connection conn = DBConnection.connect();
    PasienDAO pasienDAO = new PasienDAO();
@FXML
public void handleTambah(){

    try{

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource("/view/form_pasien.fxml"));

        Parent root = loader.load();

        FormPasienController controller =
                loader.getController();

        controller.setModeTambah();

        Stage stage = new Stage();

        stage.setTitle("Tambah Pasien");

        stage.setScene(new Scene(root));

        stage.showAndWait();

        loadData();

    }catch(Exception e){

        e.printStackTrace();
    }
}
@FXML
public void handleEdit(){
    try{
        Pasien p =tablePasien.getSelectionModel().getSelectedItem();
        if(p == null){
            showAlert("Info", "Pilih data terlebih dahulu");
            return;
        }
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/form_pasien.fxml"));
        Parent root = loader.load();
        FormPasienController controller =loader.getController();
        controller.setModeEdit(p);
        Stage stage = new Stage();
        stage.setTitle("Edit Pasien");
        // stage.setScene(new Scene(root));
        stage.setScene(new Scene(root, 800, 420));
        stage.showAndWait();
        loadData();
    }catch(Exception e){
        e.printStackTrace();
    }
}
    @FXML
        public void initialize(){
        
        // BIND TABLE COLUMN
        
        colId.setCellValueFactory(new PropertyValueFactory<>("idPasien"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colUmur.setCellValueFactory(new PropertyValueFactory<>("umur"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colHP.setCellValueFactory(new PropertyValueFactory<>("noHP"));
        colTekanan.setCellValueFactory(new PropertyValueFactory<>("tekananDarah"));
        colGula.setCellValueFactory(new PropertyValueFactory<>("gulaDarah"));
        // LOAD DATA MYSQL
        loadData();
        tablePasien.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        klikTable();
        }
    @FXML
    public void handleClose(){
        
        Stage stage =
                (Stage) tablePasien
                .getScene()
                .getWindow();

        stage.close();
    }
    // LOAD TABLE
    @FXML
    public void loadData(){
        // showAlert("Test","Load Data");
        list.clear();
        list = pasienDAO.getAllPasien();
        tablePasien.setItems(list);
    }


    // DELETE
    @FXML
    public void handleHapus(){

        pasienDAO.deletePasien(
                Integer.parseInt(txtId.getText())
        );
        loadData();
    }

    // SEARCH
    @FXML
    public void handleCari(){

        list.clear();
        list = pasienDAO.searchPasien(
                txtCari.getText()
        );
        tablePasien.setItems(list);
    }
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    } 
    private void klikTable(){

 }
}