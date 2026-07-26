package edu.utsa.cs3443.suntivity_model;

import java.util.ArrayList;
import java.util.HashSet;

public class Model {

    private HashSet<ParentAccount> parentAccounts;
    private ArrayList<Item> storeItems;
    private ArrayList<PlantStatus> plantStatuses;

    public Model() {
        parentAccounts = new HashSet<>();
        storeItems = new ArrayList<>();
        plantStatuses = new ArrayList<>();
    }

    // Parent Accounts

    public void addParentAccount(ParentAccount parent) {
        parentAccounts.add(parent);
    }

    public void removeParentAccount(ParentAccount parent) {
        parentAccounts.remove(parent);
    }

    public HashSet<ParentAccount> getParentAccounts() {
        return parentAccounts;
    }

    public ParentAccount getParentByLinkingCode(int code) {
        for (ParentAccount parent : parentAccounts) {
            if (parent.getLinkingCode() == code) {
                return parent;
            }
        }
        return null;
    }

    // Store Items

    public void addStoreItem(Item item) {
        storeItems.add(item);
    }

    public void removeStoreItem(Item item) {
        storeItems.remove(item);
    }

    public ArrayList<Item> getStoreItems() {
        return storeItems;
    }

    // Plant Statuses

    public void addPlantStatus(PlantStatus status) {
        plantStatuses.add(status);
    }

    public ArrayList<PlantStatus> getPlantStatuses() {
        return plantStatuses;
    }

    public PlantStatus getPlantStatus(String name) {
        for (PlantStatus status : plantStatuses) {
            if (status.getName().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }
}
