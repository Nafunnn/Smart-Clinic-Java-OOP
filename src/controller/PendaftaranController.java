package controller;

//import dao.PendaftaranDAO;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Pendaftaran;
import util.AlertUtil;
import util.SceneUtil;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import service.PendaftaranService;

public class PendaftaranController {
        
   private PendaftaranService  pendaftaranService =   new PendaftaranService();

   private ObservableList<Pendaftaran> masterData =FXCollections.observableArrayList();        
   
   @FXML
   private TextField txtCari;

   @FXML
   private TableView<Pendaftaran> tablePendaftaran;

   @FXML
   private TableColumn<Pendaftaran,Integer> colId;

   @FXML
   private TableColumn<Pendaftaran,String> colTanggal;

   @FXML
   private TableColumn<Pendaftaran,String> colPasien;

   @FXML
   private TableColumn<Pendaftaran,String> colDokter;

   @FXML
   private TableColumn<Pendaftaran,String> colKeluhan;

   //ObservableList<Pendaftaran> list = FXCollections.observableArrayList();
   //PendaftaranDAO dao =new PendaftaranDAO();
   
   @FXML 
   public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("idDaftar"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colPasien.setCellValueFactory(new PropertyValueFactory<>("namaPasien"));
        colDokter.setCellValueFactory(new PropertyValueFactory<>("namaDokter"));
        colKeluhan.setCellValueFactory(new PropertyValueFactory<>("keluhan"));
        loadData();
        tablePendaftaran.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void loadData() {
        masterData.clear();
        //masterData.addAll(dao.getAllPendaftaran());
        masterData.addAll(pendaftaranService.getAll());
        tablePendaftaran.setItems(masterData);
    }

    @FXML
    private void handleCari() {

        String keyword = txtCari.getText().toLowerCase();
        FilteredList<Pendaftaran> filteredData =new FilteredList<>(masterData, data -> {
                // tampil semua jika kosong
                if (keyword.isEmpty()) {return true; }
                // ambil data dengan aman
                String pasien =data.getNamaPasien() != null? data.getNamaPasien().toLowerCase(): "";
                String dokter =data.getNamaDokter() != null? data.getNamaDokter().toLowerCase(): "";
                String keluhan =data.getKeluhan() != null? data.getKeluhan().toLowerCase(): "";

                // filter
                return pasien.contains(keyword)|| dokter.contains(keyword)|| keluhan.contains(keyword);
        });

        SortedList<Pendaftaran> sortedData =new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablePendaftaran.comparatorProperty());
        tablePendaftaran.setItems(sortedData);
     }


    @FXML
    public void handleEdit(){
        try{
            Pendaftaran p =tablePendaftaran.getSelectionModel().getSelectedItem();
            if(p == null){
                AlertUtil.warning("Pilih data terlebih dahulu!");
                return;
             }
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_pendaftaran.fxml"));
             Stage stage =SceneUtil.createModal(loader,"Edit Pendaftaran",960,520);
             FormPendaftaranController controller =loader.getController();
             controller.setEditData(p);
             stage.showAndWait();
             loadData();             
        }catch(Exception e){

                e.printStackTrace();
        }
        }
        @FXML
        public void handleHapus(){
                try{
                        Pendaftaran p =tablePendaftaran.getSelectionModel().getSelectedItem();
                        if(p == null){
                                AlertUtil.warning("Pilih data terlebih dahulu!");
                                return;
                        }
                        //dao.deletePendaftaran(p.getIdDaftar());
                        pendaftaranService.delete(p.getIdDaftar());
                        loadData();
                        AlertUtil.success("Data berhasil dihapus!");
                }catch(Exception e){
                        e.printStackTrace();
                }
        }
        @FXML
        public void handleTambah(){
           try{        
                FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/form_pendaftaran.fxml"));
                Stage stage =SceneUtil.createModal(loader,"Tambah Pendaftaran",960,520);
                FormPendaftaranController controller =loader.getController();
                controller.setModeTambah();
                stage.showAndWait();
                loadData();
           }catch(Exception e){
                AlertUtil.error("Gagal membuka form");
                e.printStackTrace();
           }
        }

    @FXML
    public void handleClose(){
        Stage stage =(Stage) tablePendaftaran.getScene().getWindow();
        stage.close();
    }

}