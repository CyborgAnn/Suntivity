package edu.utsa.cs3443.suntivity_model;

public class Plant {

    private ChildAccount child;
    private PlantStatus status;
    private Color color;
    private Face face;
    private Box box;

    public Plant(ChildAccount child) {
        this.child = child;
    }

    public void setChild(ChildAccount child){ this.child = child; }
    public ChildAccount getChild(){ return child; }

    public void setStatus(PlantStatus status){ this.status = status; }
    public PlantStatus getStatus(){ return status; }

    public void setColor(Color color){ this.color = color; }
    public Color getColor(){ return color; }

    public void setFace(Face face){ this.face = face; }
    public Face getFace(){ return face; }

    public void setBox(Box box){ this.box = box; }
    public Box getBox(){ return box; }
}
