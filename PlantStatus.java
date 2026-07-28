package suntivity_model;

import java.io.File;

public class PlantStatus {

    private String name;
    private String imagePath;

    public PlantStatus(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
