package edu.utsa.cs3443.suntivity.model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Represents a child account in Suntivity.
 * A child can complete tasks, earn points, customize their plant,
 * and purchase/equip items.
 */
public class ChildAccount extends Account {

    private ParentAccount parent;
    private ArrayList<Task> tasks;
    private ArrayList<Item> items;
    private int points;
    private Plant plant;
    private ArrayList<String> notifications;

    /**
     * Creates a child account.
     *
     * @param userName child's username
     * @param password child's password
     * @param timeZone user's timezone
     */
    public ChildAccount(String userName, String password, int timeZone) {

        super(userName, password, timeZone);

        tasks = new ArrayList<>();
        items = new ArrayList<>();
        points = 0;
        plant = new Plant(this);
        notifications = new ArrayList<>();
        loadData();
    }


    /**
     * Links a child account to a parent account using a linking code.
     *
     * @param code  parent linking code
     * @param model application model containing accounts
     * @return linked parent account or null if not found
     */
    public ParentAccount linkToParent(int code, Model model) {

        ParentAccount parent = model.getParentByLinkingCode(code);

        if (parent != null) {

            parent.addChild(this);

            saveData();

        }

        return parent;

    }


    /**
     * Adds a task assigned by a parent.

        /**
         * Adds a task assigned by a parent.
         *
         * @param task task assigned to this child
         */
        public void addTask (Task task){
            tasks.add(task);
        }


        /**
         * Marks a task as completed.
         *
         * @param task task completed by the child
         */
        public void completeTask (Task task){

            task.setStatus(TaskStatus.PENDING);
        }


        /**
         * Returns all assigned tasks.
         *
         * @return list of tasks
         */
        public ArrayList<Task> getTasks () {

            return tasks;
        }


        /**
         * Returns tasks that are incomplete.
         *
         * @return list of incomplete tasks
         */
        public ArrayList<Task> getIncompleteTasks () {

            ArrayList<Task> incomplete = new ArrayList<>();

            for (Task task : tasks) {

                if (task.getStatus() == TaskStatus.INCOMPLETE) {
                    incomplete.add(task);
                }
            }

            return incomplete;
        }


        /**
         * Returns tasks that are not fully completed.
         * Includes incomplete and pending approval tasks.
         *
         * @return active tasks
         */
        public ArrayList<Task> getActiveTasks () {

            ArrayList<Task> active = new ArrayList<>();

            for (Task task : tasks) {

                if (task.getStatus() != TaskStatus.APPROVED) {
                    active.add(task);
                }
            }

            return active;
        }


        /**
         * Equips a customization item to the child's plant.
         *
         * @param item item being equipped
         */
        public void equipItem (Item item){

            if (item instanceof Color) {

                plant.setColor((Color) item);

            } else if (item instanceof Face) {

                plant.setFace((Face) item);

            } else if (item instanceof Box) {

                plant.setBox((Box) item);
            }

            saveData();
        }


        /**
         * Changes the plant status.
         *
         * @param status new plant status
         */
        public void editPlantStatus (PlantStatus status){

            plant.setStatus(status);
        }


        /**
         * Purchases an item using earned points.
         *
         * @param item item being purchased
         * @return true if purchase succeeds
         */
        public boolean purchaseItem (Item item){

            if (points >= item.getCost()) {

                points -= item.getCost();
                items.add(item);

                saveData();

                return true;
            }

            return false;
        }


        public void setPoints ( int points){

            this.points = points;
        }


        public int getPoints () {

            return points;
        }


        /**
         * Adds points earned from approved tasks.
         *
         * @param amount points earned
         */
        public void addPoints ( int amount){

            points += amount;

            updatePlant();

            saveData();


        }

        private void updatePlant () {

            if (plant == null) {
                return;
            }


            String imagePath =
                    "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/CharacterStages/";


            if (points >= 750) {

                plant.setStatus(
                        new PlantStatus(
                                "Best Health",
                                new File(
                                        imagePath + "BestHealth.png"
                                )
                        )
                );

            } else if (points >= 250) {

                plant.setStatus(
                        new PlantStatus(
                                "Middle Health",
                                new File(
                                        imagePath + "MiddleHealth.png"
                                )
                        )
                );

            } else {

                plant.setStatus(
                        new PlantStatus(
                                "Worst Health",
                                new File(
                                        imagePath + "WorstHealth.png"
                                )
                        )
                );

            }

        }


        public Plant getPlant () {

            return plant;
        }


        public ArrayList<Item> getItems () {

            return items;
        }


        public void setParent (ParentAccount parent){

            this.parent = parent;
        }


        public ParentAccount getParent () {

            return parent;
        }

        @Override
        public String toString () {

            return getUserName();

        }
        public void addNotification (String message){

            notifications.add(message);

        }


        public ArrayList<String> getNotifications () {

            return notifications;

        }


        public void clearNotifications () {

            notifications.clear();

        }

        //this saves the child's data to file//

        public void saveData () {

            try {

                File folder =
                        new File(
                                "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles"
                        );


                if (!folder.exists()) {

                    folder.mkdirs();

                }


                File file =
                        new File(
                                folder,
                                getUserName() + "_save.txt"
                        );


                PrintWriter writer =
                        new PrintWriter(
                                new FileWriter(file)
                        );


                writer.println(
                        "Username="
                                + getUserName()
                );

                writer.println(
                        "Password="
                                + getPassword()
                );


                writer.println(
                        "Points="
                                + getPoints()
                );


                if (getPlant() != null
                        && getPlant().getStatus() != null) {

                    writer.println(
                            "PlantStage="
                                    + getPlant()
                                    .getStatus()
                                    .getName()
                    );

                }


                if (getPlant() != null
                        && getPlant().getFace() != null) {

                    writer.println(
                            "Face="
                                    + getPlant()
                                    .getFace()
                                    .getName()
                    );

                }


                if (getPlant() != null
                        && getPlant().getBox() != null) {

                    writer.println(
                            "Box="
                                    + getPlant()
                                    .getBox()
                                    .getName()
                    );

                }


                writer.close();


                System.out.println(
                        "Child data saved!"
                );


            } catch (IOException e) {

                e.printStackTrace();

            }

        }


        public void loadData () {

            File file =
                    new File(
                            "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles/"
                                    + getUserName()
                                    + "_save.txt"
                    );


            if (!file.exists()) {

                System.out.println(
                        "No saved child data found."
                );

                return;

            }


            try {

                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        );


                String line;


                while ((line = reader.readLine()) != null) {

                    if(line.startsWith("Password=")){

                        String savedPassword =
                                line.substring(9);

                        setPassword(savedPassword);

                        System.out.println(
                                "Loaded child password: "
                                        + savedPassword
                        );

                    }
                    // Restore points
                    if (line.startsWith("Points=")) {


                        this.points =
                                Integer.parseInt(
                                        line.substring(7)
                                );

                    }


                    // Restore plant stage
                    if (line.startsWith("PlantStage=")
                            && plant != null) {


                        String stage =
                                line.substring(11);


                        String path =
                                "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/CharacterStages/";


                        if (stage.equals("Best Health")) {


                            plant.setStatus(
                                    new PlantStatus(
                                            "Best Health",
                                            new File(
                                                    path + "BestHealth.png"
                                            )
                                    )
                            );

                        } else if (stage.equals("Middle Health")) {


                            plant.setStatus(
                                    new PlantStatus(
                                            "Middle Health",
                                            new File(
                                                    path + "MiddleHealth.png"
                                            )
                                    )
                            );

                        } else {


                            plant.setStatus(
                                    new PlantStatus(
                                            "Worst Health",
                                            new File(
                                                    path + "WorstHealth.png"
                                            )
                                    )
                            );

                        }

                    }


                    // Restore face
                    if (line.startsWith("Face=")
                            && plant != null) {


                        String faceName =
                                line.substring(5);


                        String imagePath =
                                "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/CharacterCustomization/Faces/";


                        if (faceName.equals("Face 1")) {

                            plant.setFace(
                                    new Face(
                                            20,
                                            "Face 1",
                                            new File(
                                                    imagePath + "Face1.png"
                                            )
                                    )
                            );

                        }


                        if (faceName.equals("Face 2")) {

                            plant.setFace(
                                    new Face(
                                            20,
                                            "Face 2",
                                            new File(
                                                    imagePath + "Face2.png"
                                            )
                                    )
                            );

                        }


                        if (faceName.equals("Face 3")) {

                            plant.setFace(
                                    new Face(
                                            20,
                                            "Face 3",
                                            new File(
                                                    imagePath + "Face3.png"
                                            )
                                    )
                            );

                        }


                        if (faceName.equals("Face 4")) {

                            plant.setFace(
                                    new Face(
                                            20,
                                            "Face 4",
                                            new File(
                                                    imagePath + "Face4.png"
                                            )
                                    )
                            );

                        }


                        if (faceName.equals("Face 5")) {

                            plant.setFace(
                                    new Face(
                                            20,
                                            "Face 5",
                                            new File(
                                                    imagePath + "Face5.png"
                                            )
                                    )
                            );

                        }


                    }


                    // Restore pot
                    if (line.startsWith("Box=")
                            && plant != null) {


                        String boxName =
                                line.substring(4);


                        String imagePath =
                                "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/CharacterCustomization/PlantBoxes/";


                        if (boxName.equals("Plant Pot 1")) {


                            plant.setBox(
                                    new Box(
                                            50,
                                            "Plant Pot 1",
                                            new File(
                                                    imagePath + "PlantPot1.png"
                                            )
                                    )
                            );

                        }


                        if (boxName.equals("Plant Pot 2")) {


                            plant.setBox(
                                    new Box(
                                            50,
                                            "Plant Pot 2",
                                            new File(
                                                    imagePath + "PlantPot2.png"
                                            )
                                    )
                            );

                        }


                        if (boxName.equals("Plant Pot 3")) {


                            plant.setBox(
                                    new Box(
                                            50,
                                            "Plant Pot 3",
                                            new File(
                                                    imagePath + "PlantPot3.png"
                                            )
                                    )
                            );

                        }


                        if (boxName.equals("Plant Pot 4")) {


                            plant.setBox(
                                    new Box(
                                            50,
                                            "Plant Pot 4",
                                            new File(
                                                    imagePath + "PlantPot4.png"
                                            )
                                    )
                            );

                        }


                        if (boxName.equals("Plant Pot 5")) {


                            plant.setBox(
                                    new Box(
                                            50,
                                            "Plant Pot 5",
                                            new File(
                                                    imagePath + "PlantPot5.png"
                                            )
                                    )
                            );

                        }


                    }


                }


                reader.close();


                System.out.println(
                        "Child data loaded!"
                );


            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }
