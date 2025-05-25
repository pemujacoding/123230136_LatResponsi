/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Mahasiswa.*;
import View.Mahasiswa.*;
import java.util.List;
import javax.swing.*;

public class ControllerMahasiswa {
    MahasiswaView halamanMahasiswa;
    
    MahasiswaDAO daoMahasiswa;
    
    List<ModelMahasiswa> daftarMahasiswa;
    public ControllerMahasiswa (MahasiswaView halamanMahasiswa){
        this.halamanMahasiswa = halamanMahasiswa;
        daoMahasiswa = new MahasiswaDAO();
    }
    
    public void showAllMahasiswa() {
        daftarMahasiswa = daoMahasiswa.getAll();
        ModelTabelMahasiswa table = new ModelTabelMahasiswa(daftarMahasiswa);
        halamanMahasiswa.getTableMahasiswa().setModel(table);
    }
    
    public void insertMahasiswa() {
        try {
            ModelMahasiswa mahasiswaBaru = new ModelMahasiswa();
            String nama = halamanMahasiswa.getNamaInput();
            String nim = halamanMahasiswa.getNimInput();
            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            mahasiswaBaru.setNama(nama);
            mahasiswaBaru.setNim(nim);
            daoMahasiswa.insert(mahasiswaBaru);
            
            JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editMahasiswa(int id) {
        try {
            ModelMahasiswa mahasiswaYangMauDiedit = new ModelMahasiswa();
            String nama = halamanMahasiswa.getNamaInput();
            String nim = halamanMahasiswa.getNimInput();
            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            mahasiswaYangMauDiedit.setId(id);
            mahasiswaYangMauDiedit.setNama(nama);
            mahasiswaYangMauDiedit.setNim(nim);
            daoMahasiswa.update(mahasiswaYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diubah.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        Integer id = (int) halamanMahasiswa.getTableMahasiswa().getValueAt(baris, 0);
        String nama = halamanMahasiswa.getTableMahasiswa().getValueAt(baris, 1).toString();
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );
        if (input == 0) {
            daoMahasiswa.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
        }
    }
}