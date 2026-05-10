package controller;

import dao.DokterDAO;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Dokter;

public class DokterController {

    @FXML
    private TextField txtId, txtCari;

    @FXML
    private TableView<Dokter> tableDokter;

    @FXML
    private TableColumn<Dokter,Integer> colId;

    @FXML
    private TableColumn<Dokter,String> colNama;

    @FXML
    private TableColumn<Dokter,String> colSpesialis;

    @FXML
    private TableColumn<Dokter,String> colHP;

    ObservableList<Dokter> list =
            FXCollections.observableArrayList();

    DokterDAO dokterDAO =
            new DokterDAO();

    @FXML
    public void initialize(){

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idDokter"));

        colNama.setCellValueFactory(
                new PropertyValueFactory<>("nama"));

        colSpesialis.setCellValueFactory(
                new PropertyValueFactory<>("spesialis"));

        colHP.setCellValueFactory(
                new PropertyValueFactory<>("noHP"));

        tableDokter.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);

        loadData();
    }

    @FXML
    public void loadData(){

        list.clear();

        list = dokterDAO.getAllDokter();

        tableDokter.setItems(list);
    }

    @FXML
    public void handleTambah(){

        try{

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/view/form_dokter.fxml"));

            Parent root = loader.load();

            FormDokterController controller =
                    loader.getController();

            controller.setModeTambah();

            Stage stage = new Stage();

            stage.setTitle("Tambah Dokter");

            stage.setScene(new Scene(root,600,350));

            stage.showAndWait();

            loadData();

        }catch(Exception e){

            e.printStackTrace();
        }
    }
    @FXML
    public void handleEdit(){

        Dokter d =
                tableDokter.getSelectionModel()
                        .getSelectedItem();

        if(d == null){

            Alert alert =
                    new Alert(Alert.AlertType.WARNING);

            alert.setContentText(
                    "Pilih data dokter dahulu!");

            alert.show();

            return;
        }

        try{

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_dokter.fxml"));

            Parent root = loader.load();

            FormDokterController controller =
                    loader.getController();

            controller.setModeEdit(d);

            Stage stage = new Stage();

            stage.setTitle("Edit Dokter");

            stage.setScene(
                    new Scene(root,600,350));

            stage.showAndWait();

            loadData();

        }catch(Exception e){

            e.printStackTrace();
        }
    }
        
    @FXML
    public void handleHapus(){

        Dokter d =
                tableDokter.getSelectionModel()
                        .getSelectedItem();

        if(d == null){

            Alert alert =
                    new Alert(Alert.AlertType.WARNING);

            alert.setContentText(
                    "Pilih data dokter dahulu!");

            alert.show();

            return;
        }

        dokterDAO.deleteDokter(
                d.getIdDokter());

        loadData();
    }

    @FXML
    public void handleCari(){

        list.clear();

        list = dokterDAO.searchDokter(
                txtCari.getText());

        tableDokter.setItems(list);
    }

    @FXML
    public void handleClose(){

        Stage stage =
                (Stage) tableDokter
                        .getScene()
                        .getWindow();

        stage.close();
    }
}