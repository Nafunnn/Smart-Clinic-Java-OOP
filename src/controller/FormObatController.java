package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Obat;
import service.ObatService;
import util.AlertUtil;
import util.ValidationUtil;

public class FormObatController {

    @FXML
    private TextField txtId,
            txtNama,
            txtStok,
            txtHarga,
            txtAturanPakai,
            txtKodeKfa;

    private ObatService obatService = new ObatService();
    private boolean modeEdit = false;
    private int idObat;

    public void setModeTambah() {
        modeEdit = false;
    }

    public void setModeEdit(Obat obat) {
        modeEdit = true;
        idObat = obat.getIdObat();
        txtId.setText(String.valueOf(obat.getIdObat()));
        txtNama.setText(obat.getNamaObat());
        txtStok.setText(String.valueOf(obat.getStok()));
        txtHarga.setText(String.valueOf(obat.getHarga()));
        txtAturanPakai.setText(
                obat.getAturanPakai() != null ? obat.getAturanPakai() : "");
        txtKodeKfa.setText(
                obat.getKodeKfa() > 0 ? String.valueOf(obat.getKodeKfa()) : "");
    }

    @FXML
    public void handleSimpan() {
        try {
            if (ValidationUtil.isEmpty(txtNama, "Nama obat wajib diisi")) {
                return;
            }
            if (ValidationUtil.isEmpty(txtStok, "Stok wajib diisi")) {
                return;
            }
            if (ValidationUtil.isEmpty(txtHarga, "Harga wajib diisi")) {
                return;
            }

            int stok;
            double harga;
            int kodeKfa = 0;

            try {
                stok = Integer.parseInt(txtStok.getText().trim());
            } catch (NumberFormatException e) {
                AlertUtil.warning("Stok harus berupa angka");
                return;
            }

            try {
                harga = Double.parseDouble(txtHarga.getText().trim());
            } catch (NumberFormatException e) {
                AlertUtil.warning("Harga harus berupa angka");
                return;
            }

            String kodeText = txtKodeKfa.getText().trim();
            if (!kodeText.isEmpty()) {
                try {
                    kodeKfa = Integer.parseInt(kodeText);
                } catch (NumberFormatException e) {
                    AlertUtil.warning("Kode KFA harus berupa angka");
                    return;
                }
            }

            Obat obat = new Obat(
                    modeEdit ? idObat : 0,
                    txtNama.getText().trim(),
                    stok,
                    harga,
                    txtAturanPakai.getText().trim(),
                    kodeKfa);

            if (modeEdit) {
                obatService.update(obat);
                AlertUtil.success("Data obat berhasil diupdate");
            } else {
                obatService.insert(obat);
                AlertUtil.success("Data obat berhasil disimpan");
            }
            closeWindow();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBatal() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) txtNama.getScene().getWindow();
        stage.close();
    }
}
