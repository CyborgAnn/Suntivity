package edu.utsa.cs3443.suntivity.model;

import java.util.ArrayList;
import java.io.File;

/**
 * Stores and manages application data for Suntivity.
 * This class acts as the central model containing user accounts.
 */
public class Model {

    private ArrayList<ParentAccount> parents;
    private ArrayList<ChildAccount> children;


    /**
     * Creates an empty model.
     */
    public Model() {

        parents = new ArrayList<>();
        children = new ArrayList<>();

        loadSavedAccounts();

    }


    /**
     * Adds a parent account to the model.
     *
     * @param parent parent account to add
     */
    public void addParent(ParentAccount parent) {

        if (!parents.contains(parent)) {

            parents.add(parent);

        }

    }


    /**
     * Adds a child account to the model.
     *
     * @param child child account to add
     */
    public void addChild(ChildAccount child) {

        if (!children.contains(child)) {

            children.add(child);

        }

    }


    /**
     * Finds a parent account using a linking code.
     *
     * @param code parent's linking code
     * @return matching parent account
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
     * Checks login credentials.
     */
    public Account validateLogin(
            String username,
            String password) {


        for (ParentAccount parent : parents) {

            if(parent.getUserName().equals(username)
                    && parent.getPassword().equals(password)){

                return parent;

            }

        }



        for (ChildAccount child : children) {

            if(child.getUserName().equals(username)
                    && child.getPassword().equals(password)){

                return child;

            }

        }


        return null;

    }



    /**
     * Checks if username already exists.
     */
    public boolean usernameExists(String username) {


        for(ParentAccount parent : parents){

            if(parent.getUserName().equals(username)){

                return true;

            }

        }



        for(ChildAccount child : children){

            if(child.getUserName().equals(username)){

                return true;

            }

        }


        return false;

    }



    public ArrayList<ParentAccount> getParents(){

        return parents;

    }



    public ArrayList<ChildAccount> getChildren(){

        return children;

    }



    public ParentAccount findParentByCode(String code){

        try{

            int linkingCode =
                    Integer.parseInt(code);

            return getParentByLinkingCode(linkingCode);

        }
        catch(NumberFormatException e){

            return null;

        }

    }



    public ChildAccount getChildByUsername(String username){


        for(ChildAccount child : children){


            if(child.getUserName().equals(username)){


                return child;

            }

        }


        return null;

    }




    /**
     * Loads saved accounts.
     */
    private void loadSavedAccounts(){


        loadChildren();


        loadParents();


        System.out.println(
                "Saved accounts loaded."
        );

    }




    /**
     * Loads child accounts from save files.
     */
    private void loadChildren(){


        File folder =
                new File(
                        "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles"
                );


        File[] files =
                folder.listFiles();



        if(files == null){

            return;

        }



        for(File file : files){


            if(file.getName().endsWith("_save.txt")
                    && !file.getName().contains("_parentSave")){


                String username =
                        file.getName()
                                .replace("_save.txt","");



                ChildAccount child =
                        new ChildAccount(
                                username,
                                "",
                                0
                        );



                children.add(child);



                System.out.println(
                        "Loaded child: "
                                + username
                );


            }

        }

    }




    /**
     * Loads parent accounts from save files.
     */
    private void loadParents(){


        File folder =
                new File(
                        "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles"
                );


        File[] files =
                folder.listFiles();



        if(files == null){

            return;

        }




        for(File file : files){


            if(file.getName().endsWith("_parentSave.txt")){


                String username =
                        file.getName()
                                .replace("_parentSave.txt","");



                ParentAccount parent =
                        new ParentAccount(
                                username,
                                "temp",
                                0,
                                this
                        );



                parents.add(parent);



                System.out.println(
                        "Loaded parent: "
                                + username
                );


            }

        }


    }


}