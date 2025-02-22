package com.megacity.services.impl;

import com.megacity.dao.VehicleAlbumDAO;
import com.megacity.models.VehicleAlbum;
import com.megacity.services.VehicleAlbumService;
import java.sql.SQLException;
import java.util.List;

public class VehicleAlbumServiceImpl implements VehicleAlbumService {

    private VehicleAlbumDAO vehicleAlbumDAO = new VehicleAlbumDAO();

    @Override
    public void addVehicle(VehicleAlbum vehicle) {
        try {
            vehicleAlbumDAO.insertVehicle(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<VehicleAlbum> getAllVehicles() {
        try {
            return vehicleAlbumDAO.selectAllVehicles();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}