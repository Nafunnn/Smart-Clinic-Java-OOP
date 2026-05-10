package service;

import dao.PemeriksaanDAO;
import dao.ResepObatDAO;

import javafx.collections.ObservableList;

import model.Pemeriksaan;
import model.RekamMedis;
import model.ResepObat;

public class PemeriksaanService {

    // =========================
    // DAO
    // =========================
    private PemeriksaanDAO dao =new PemeriksaanDAO();
    private ResepObatDAO resepDAO =new ResepObatDAO();

    // =========================
    // GET ALL
    // =========================
    public ObservableList<Pemeriksaan> getAll(){
        return dao.getData();
    }

    // =========================
    // SEARCH
    // =========================
    public ObservableList<Pemeriksaan> search(String keyword){
        return dao.search(keyword);
    }

    // =========================
    // DELETE
    // =========================
    public void delete(int id)throws Exception{
        dao.deletePemeriksaan(id);
    }

    // =========================
    // GET RESEP
    // =========================
    public ObservableList<ResepObat>
    getResep(int idPeriksa){
        return resepDAO.getByPemeriksaan(idPeriksa);
    }
    // =========================
    // SIMPAN
    // =========================
    public void simpan(Pemeriksaan p,ObservableList<ResepObat> listResep,RekamMedis rekam,boolean editMode) throws Exception {
        // VALIDASI
        if(p.getPendaftaran() == null){
            throw new Exception("Pendaftaran wajib dipilih");
        }
        if(p.getDiagnosa() == null || p.getDiagnosa().trim().isEmpty()){
            throw new Exception("Diagnosa wajib diisi");
        }

        if(listResep == null|| listResep.isEmpty()){
            throw new Exception("Minimal 1 obat");
        }

        // INSERT / UPDATE

        if(editMode){
            dao.updatePemeriksaan(p,listResep,rekam);
        }else{
            dao.insertPemeriksaan(p,listResep,rekam);
        }
    }

    // =========================
    // GET RESEP
    // =========================
    public ObservableList<ResepObat> getResepByPemeriksaan(int idPeriksa){
        return dao.getResepByPemeriksaan(idPeriksa);
    }
}