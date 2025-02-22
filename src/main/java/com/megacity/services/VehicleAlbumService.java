package com.megacity.services;

import com.megacity.models.VehicleAlbum;
import java.util.List;

public interface VehicleAlbumService {
    void addVehicle(VehicleAlbum vehicle);
    List<VehicleAlbum> getAllVehicles();
}