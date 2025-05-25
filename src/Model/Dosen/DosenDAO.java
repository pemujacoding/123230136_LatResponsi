/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Dosen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Connector;

public class DosenDAO {
    public void insert(ModelDosen dosen) {
       try {
            String query = "INSERT INTO dosen (nama, nidn) VALUES (?, ?);";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, dosen.getNama());
            statement.setString(2, dosen.getNidn());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }

    public void update(ModelDosen dosen) {
        try {
            String query = "UPDATE dosen SET nama=?, nidn=? WHERE id=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, dosen.getNama());
            statement.setString(2, dosen.getNidn());
            statement.setInt(3, dosen.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }

    public void delete(int id) {
        try {
            // Perintah query disimpan ke dalam variabel "query"
            String query = "DELETE FROM dosen WHERE id=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    public List<ModelDosen> getAll() {
         List<ModelDosen> listDosen = null;

        try {
            listDosen = new ArrayList<>();
            Statement statement = Connector.Connect().createStatement();
            String query = "SELECT * FROM dosen;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ModelDosen dsn = new ModelDosen();
                dsn.setId(resultSet.getInt("id"));
                dsn.setNama(resultSet.getString("nama"));
                dsn.setNidn(resultSet.getString("nidn"));
                listDosen.add(dsn);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listDosen;
    }
}
