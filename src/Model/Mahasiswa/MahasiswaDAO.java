/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Connector;

public class MahasiswaDAO {
    public void insert(ModelMahasiswa mahasiswa) {
       try {
            String query = "INSERT INTO mahasiswa (nama, nim) VALUES (?, ?);";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }

    public void update(ModelMahasiswa mahasiswa) {
        try {
            String query = "UPDATE mahasiswa SET nama=?, nim=? WHERE id=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            statement.setInt(3, mahasiswa.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }

    public void delete(int id) {
        try {
            // Perintah query disimpan ke dalam variabel "query"
            String query = "DELETE FROM mahasiswa WHERE id=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    public List<ModelMahasiswa> getAll() {
         List<ModelMahasiswa> listMahasiswa = null;

        try {
            listMahasiswa = new ArrayList<>();
            Statement statement = Connector.Connect().createStatement();
            String query = "SELECT * FROM mahasiswa;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ModelMahasiswa mhs = new ModelMahasiswa();
                mhs.setId(resultSet.getInt("id"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setNim(resultSet.getString("nim"));
                listMahasiswa.add(mhs);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listMahasiswa;
    }
}
