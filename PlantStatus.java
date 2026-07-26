package suntivity_model;

import java.io.File;

public class PlantStatus {

    private String name;
    private File image;

    public PlantStatus(String name, File image) {
        this.name = name;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public File getImage() {
        return image;
    }
}
