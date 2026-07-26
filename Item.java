package edu.utsa.cs3443.suntivity_model;

public abstract class Item {

    private int cost;
    private String name;

    public Item(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public int getCost(){
        return cost;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
