package com.megacity.dao;

import com.megacity.models.VehicleAlbum;
import com.megacity.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleAlbumDAO {

    private static final String INSERT_VEHICLE_SQL = "INSERT INTO vehicle_album (name, type, image, description) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_VEHICLES = "SELECT * FROM vehicle_album";

    public void insertVehicle(VehicleAlbum vehicle) throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEHICLE_SQL)) {
            preparedStatement.setString(1, vehicle.getName());
            preparedStatement.setString(2, vehicle.getType());
            preparedStatement.setString(3, vehicle.getImage());
            preparedStatement.setString(4, vehicle.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    public List<VehicleAlbum> selectAllVehicles() throws SQLException, ClassNotFoundException {
        List<VehicleAlbum> vehicles = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEHICLES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String image = rs.getString("image");
                String description = rs.getString("description");
                vehicles.add(new VehicleAlbum(name, type, image, description));
            }
        }
        return vehicles;
    }
}