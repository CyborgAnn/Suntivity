package suntivity_model;

public class Face extends Item {

    private String imagePath;

    public Face(int cost, String name, String imagePath) {
        super(cost, name);
        this.imagePath = imagePath;
    }

    public void setImage(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImage(){
        return imagePath;
    }
}
