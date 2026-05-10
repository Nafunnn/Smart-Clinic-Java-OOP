package controller;

import dao.DokterDAO;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import model.Dokter;
import util.AlertUtil;
import util.SceneUtil;
import service.DokterService;

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

    //DokterDAO dokterDAO =       new DokterDAO();
    private DokterService dokterService =new DokterService();

    @FXML
    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("idDokter"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colSpesialis.setCellValueFactory(new PropertyValueFactory<>("spesialis"));
        colHP.setCellValueFactory(new PropertyValueFactory<>("noHP"));
        tableDokter.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();
    }

    @FXML
    public void loadData(){
        list.clear();
        list = dokterService.getAll();
        tableDokter.setItems(list);
    }
    @FXML
    public void handleTambah() {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/form_dokter.fxml"));
            Stage stage =SceneUtil.createModal(loader,"Tambah Dokter",800,420);
            FormDokterController controller =loader.getController();
            controller.setModeTambah();
            stage.showAndWait();
            loadData();

        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleEdit() {
        Dokter dokter =tableDokter.getSelectionModel().getSelectedItem();
        if (dokter == null) {
            AlertUtil.warning("Pilih data dokter dahulu!");
            return;
        }
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/form_dokter.fxml"));
            Stage stage =SceneUtil.createModal(loader,"Edit Dokter",800,420);
            FormDokterController controller =loader.getController();
            controller.setModeEdit(dokter);
            stage.showAndWait();
            loadData();
        } catch (Exception e) {
            AlertUtil.error("Gagal membuka form edit");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleHapus(){
        try{
            Dokter dokter =tableDokter.getSelectionModel().getSelectedItem();
            if(dokter == null){
                AlertUtil.warning("Pilih data dokter dahulu!");
                return;
            }
            dokterService.delete(dokter.getIdDokter());
            AlertUtil.success("Data berhasil dihapus");
            loadData();

        }catch(Exception e){
            AlertUtil.error(e.getMessage());
            e.printStackTrace();
        }
    }        
    // @FXML
    // public void handleHapus(){
    //     Dokter d =tableDokter.getSelectionModel().getSelectedItem();
    //     if(d == null){
    //         Alert alert =
    //                 new Alert(Alert.AlertType.WARNING);
    //         alert.setContentText(
    //                 "Pilih data dokter dahulu!");
    //         alert.show();
    //         return;
    //     }
    //     dokterDAO.deleteDokter(d.getIdDokter());
    //     loadData();
    // }

    @FXML
    public void handleCari(){

        list.clear();
        list = dokterService.search(txtCari.getText());
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