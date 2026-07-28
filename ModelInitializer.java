package suntivity_model;

public class ModelInitializer {

    //Initializes model without default accounts
    public static Model initializeModel(){

        //Create model object
        Model suntivityModel = new Model();

        //Initialize PlantStatus Objects
        PlantStatus bestHealth = new PlantStatus("Best Health", "/CharacterStages/BestHealth.png");
        PlantStatus middleHealth = new PlantStatus("Middle Health", "/CharacterStages/MiddleHealth.png");
        PlantStatus worstHealth = new PlantStatus("Worst Health", "/CharacterStages/WorstHealth.png");

        //Add PlantStatus objects to model
        suntivityModel.addPlantStatus(bestHealth);
        suntivityModel.addPlantStatus(middleHealth);
        suntivityModel.addPlantStatus(worstHealth);

        //Initialize Face Objects
        Face face1 = new Face(100, "Face 1", "/CharacterCustomization/Face1.png");
        Face face2 = new Face(100, "Face 2", "/CharacterCustomization/Face2.png");
        Face face3 = new Face(100, "Face 3", "/CharacterCustomization/Face3.png");
        Face face4 = new Face(100, "Face 4", "/CharacterCustomization/Face4.png");
        Face face5 = new Face(100, "Face 5", "/CharacterCustomization/Face5.png");
        Face face6 = new Face(100, "Face 6", "/CharacterCustomization/Face6.png");

        //Add Face objects to model
        suntivityModel.addStoreItem(face1);
        suntivityModel.addStoreItem(face2);
        suntivityModel.addStoreItem(face3);
        suntivityModel.addStoreItem(face4);
        suntivityModel.addStoreItem(face5);
        suntivityModel.addStoreItem(face6);

        //Initialize Box Objects
        Box box1 = new Box(100, "Plant Pot 1", "/CharacterCustomization/PlantPot1.png");
        Box box2 = new Box(100, "Plant Pot 2", "/CharacterCustomization/PlantPot2.png");
        Box box3 = new Box(100, "Plant Pot 3", "/CharacterCustomization/PlantPot3.png");
        Box box4 = new Box(100, "Plant Pot 4", "/CharacterCustomization/PlantPot4.png");
        Box box5 = new Box(100, "Plant Pot 5", "/CharacterCustomization/PlantPot5.png");

        //Add Box objects to model
        suntivityModel.addStoreItem(box1);
        suntivityModel.addStoreItem(box2);
        suntivityModel.addStoreItem(box3);
        suntivityModel.addStoreItem(box4);
        suntivityModel.addStoreItem(box5);

        //Initialize Color objects
        Color color1 = new Color(100, "Red", "#FF0000");
        Color color2 = new Color(100, "Yellow", "#FFC400");
        Color color3 = new Color(100, "Green", "#00FF44");
        Color color4 = new Color(100, "Blue", "#00FFF6");
        Color color5 = new Color(100, "Purple", "#FF00FF");

        //Add Color objects to model
        suntivityModel.addStoreItem(color1);
        suntivityModel.addStoreItem(color2);
        suntivityModel.addStoreItem(color3);
        suntivityModel.addStoreItem(color4);
        suntivityModel.addStoreItem(color5);

        //return the model
        return suntivityModel;
    }

    //Initializes model with default accounts
    public static Model initializeModelPlus(){

        //Create model object and initialize model without default accounts
        Model suntivityModel = ModelInitializer.initializeModel();

        //Initialize parent and child accounts
        ParentAccount parent1 = new ParentAccount("Emperor Palpatine", "SomehowIReturned", 1, suntivityModel);
        ChildAccount child1 = new ChildAccount("Darth Vader", "SlayerofYounglings9000", 1);
        ChildAccount child2 = new ChildAccount("Stormtrooper 1", "ICan'tAim3000", 1);
        ChildAccount child3 = new ChildAccount("Stormtrooper 2", "Empire4Life", 2);

        //Initialize point amounts
        child1.setPoints(1000);
        child2.setPoints(5);
        child3.setPoints(100);

        //Initialize plant statuses
        child1.editPlantStatus(suntivityModel.getPlantStatus("Best Health"));
        child2.editPlantStatus(suntivityModel.getPlantStatus("Worst Health"));
        child3.editPlantStatus(suntivityModel.getPlantStatus("Middle Health"));

        //Purchase items for children
        child1.purchaseItem(suntivityModel.getStoreItem("Red"));
        child1.purchaseItem(suntivityModel.getStoreItem("Face 5"));
        child1.purchaseItem(suntivityModel.getStoreItem("Plant Pot 5"));
        child1.purchaseItem(suntivityModel.getStoreItem("Plant Pot 1"));
        child3.purchaseItem(suntivityModel.getStoreItem("Face 1"));

        //Equip items for children
        child1.equipItem(child1.getItem("Red"));
        child1.equipItem(child1.getItem("Face 5"));
        child1.equipItem(child1.getItem("Plant Pot 5"));
        child3.equipItem(child1.getItem("Face 1"));

        //Add Accounts to model
        suntivityModel.addAccount(parent1);
        suntivityModel.addAccount(child1);
        suntivityModel.addAccount(child2);
        suntivityModel.addAccount(child3);

        //Link children to parent
        child1.linkToParent(parent1.getLinkingCode(), suntivityModel);
        child2.linkToParent(parent1.getLinkingCode(), suntivityModel);
        child3.linkToParent(parent1.getLinkingCode(), suntivityModel);

        //Create tasks for children
        Task task1_1 = new Task(child1, parent1, "Defeat Rebels", "I don't want these rebel scum getting anywhere near my Death Star. Destroy them at once!", 5, 25, 1200, 100);
        Task task1_2 = new Task(child1, parent1, "Imperial Meeting", "I know you hate these meetings and that you would much rather be choking younglings, but they are essential to the operation of the empire.", 5, 26, 1600, 50);
        Task task2_1 = new Task(child2, parent1, "Leave Death Star", "Your aim is abysmal, as a result you are fired from the Death Star and must leave immediately.", 5, 25, 1000, 1);
        Task task2_2 = new Task(child2, parent1, "Aim Practice", "Your aim is abysmal, now must redo basic training at the imperial camp.", 6, 12, 800, 10);
        Task task3_1 = new Task(child3, parent1, "Clean Garbage Compactor", "As punishment for letting the rebels escape, you must now clean up the garbage compactor.", 5, 26, 1800, 1);

        //Set complete tasks
        child2.completeTask(task2_1);
        child3.completeTask(task3_1);

        //return the model
        return suntivityModel;
    }
}
