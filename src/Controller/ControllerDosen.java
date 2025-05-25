/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Dosen.*;
import View.Dosen.*;
import java.util.List;
import javax.swing.*;

public class ControllerDosen {
    DosenView halamanDosen;
    
    DosenDAO daoDosen;
    
    List<ModelDosen> daftarDosen;
    public ControllerDosen (DosenView halamanDosen){
        this.halamanDosen = halamanDosen;
        daoDosen = new DosenDAO();
    }
    
    public void showAllDosen() {
        daftarDosen = daoDosen.getAll();
        ModelTabelDosen table = new ModelTabelDosen(daftarDosen);
        halamanDosen.getTableDosen().setModel(table);
    }
    
    public void insertDosen() {
        try {
            ModelDosen dosenBaru = new ModelDosen();
            String nama = halamanDosen.getNamaInput();
            String nidn = halamanDosen.getNidnInput();
            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIDN tidak boleh kosong!");
            }
            dosenBaru.setNama(nama);
            dosenBaru.setNidn(nidn);
            daoDosen.insert(dosenBaru);
            
            JOptionPane.showMessageDialog(null, "Dosen baru berhasil ditambahkan.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editDosen(int id) {
        try {
            ModelDosen dosenYangMauDiedit = new ModelDosen();
            String nama = halamanDosen.getNamaInput();
            String nidn = halamanDosen.getNidnInput();
            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIM NIDN boleh kosong!");
            }
            dosenYangMauDiedit.setId(id);
            dosenYangMauDiedit.setNama(nama);
            dosenYangMauDiedit.setNidn(nidn);
            daoDosen.update(dosenYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data dosen berhasil diubah.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {
        Integer id = (int) halamanDosen.getTableDosen().getValueAt(baris, 0);
        String nama = halamanDosen.getTableDosen().getValueAt(baris, 1).toString();
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );
        if (input == 0) {
            daoDosen.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
        }
    }
}