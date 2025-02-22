package com.megacity.models;

public class VehicleAlbum {
    private String name;
    private String type;
    private String image;
    private String description;

    public VehicleAlbum(String name, String type, String image, String description) {
    	this.name = name;
    	this.type = type;
    	this.image = image;
    	this.description = description;
	}

	// Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}