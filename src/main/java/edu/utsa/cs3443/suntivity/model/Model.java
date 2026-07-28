package edu.utsa.cs3443.suntivity.model;

import java.util.ArrayList;

/**
 * Stores and manages application data for Suntivity.
 * This class acts as the central model containing user accounts.
 */
public class Model {

    private ArrayList<ParentAccount> parents;

    /**
     * Creates an empty model.
     */
    public Model() {
        parents = new ArrayList<>();
    }

    /**
     * Adds a parent account to the model.
     *
     * @param parent parent account to add
     */
    public void addParent(ParentAccount parent) {
        parents.add(parent);
    }

    /**
     * Finds a parent account using a linking code.
     *
     * @param code parent's linking code
     * @return matching parent account or null if not found
     */
    public ParentAccount getParentByLinkingCode(int code) {

        for (ParentAccount parent : parents) {

            if (parent.getLinkingCode() == code) {
                return parent;
            }
        }

        return null;
    }

    /**
     * Returns all parent accounts.
     *
     * @return list of parents
     */
    public ArrayList<ParentAccount> getParents() {
        return parents;
    }
}