package suntivity_model;

import java.io.File;

public class Face extends Item {

    private File image;

    public Face(int cost, String name, File image) {
        super(cost, name);
        this.image = image;
    }

    public void setImage(File image){
        this.image = image;
    }

    public File getImage(){
        return image;
    }
}
