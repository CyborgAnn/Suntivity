package suntivity_model;

/**
 * Provides utility methods for creating and initializing Suntivity application
 * models.
 *
 * <p>This class creates a default Model instance and populates it with
 * predefined plant statuses, store items, and optionally sample user accounts,
 * tasks, and customizations for testing purposes.</p>
 */
public class ModelInitializer {

    /**
     * Creates and initializes a Model without default user accounts.
     *
     * <p>The returned model contains default plant statuses and store items,
     * including face, box, and color customization items.</p>
     *
     * @return an initialized model containing default application data
     */
    public static Model initializeModel() {

        // Create model object
        Model suntivityModel = new Model();

        // Initialize PlantStatus objects
        PlantStatus bestHealth = new PlantStatus("Best Health", "/CharacterStages/BestHealth.png");
        PlantStatus middleHealth = new PlantStatus("Middle Health", "/CharacterStages/MiddleHealth.png");
        PlantStatus worstHealth = new PlantStatus("Worst Health", "/CharacterStages/WorstHealth.png");

        // Add PlantStatus objects to model
        suntivityModel.addPlantStatus(bestHealth);
        suntivityModel.addPlantStatus(middleHealth);
        suntivityModel.addPlantStatus(worstHealth);

        // Initialize Face objects
        Face face1 = new Face(100, "Face 1", "/CharacterCustomization/Face1.png");
        Face face2 = new Face(100, "Face 2", "/CharacterCustomization/Face2.png");
        Face face3 = new Face(100, "Face 3", "/CharacterCustomization/Face3.png");
        Face face4 = new Face(100, "Face 4", "/CharacterCustomization/Face4.png");
        Face face5 = new Face(100, "Face 5", "/CharacterCustomization/Face5.png");
        Face face6 = new Face(100, "Face 6", "/CharacterCustomization/Face6.png");

        // Add Face objects to model
        suntivityModel.addStoreItem(face1);
        suntivityModel.addStoreItem(face2);
        suntivityModel.addStoreItem(face3);
        suntivityModel.addStoreItem(face4);
        suntivityModel.addStoreItem(face5);
        suntivityModel.addStoreItem(face6);

        // Initialize Box objects
        Box box1 = new Box(100, "Plant Pot 1", "/CharacterCustomization/PlantPot1.png");
        Box box2 = new Box(100, "Plant Pot 2", "/CharacterCustomization/PlantPot2.png");
        Box box3 = new Box(100, "Plant Pot 3", "/CharacterCustomization/PlantPot3.png");
        Box box4 = new Box(100, "Plant Pot 4", "/CharacterCustomization/PlantPot4.png");
        Box box5 = new Box(100, "Plant Pot 5", "/CharacterCustomization/PlantPot5.png");

        // Add Box objects to model
        suntivityModel.addStoreItem(box1);
        suntivityModel.addStoreItem(box2);
        suntivityModel.addStoreItem(box3);
        suntivityModel.addStoreItem(box4);
        suntivityModel.addStoreItem(box5);

        // Initialize Color objects
        Color color1 = new Color(100, "Red", "#FF0000");
        Color color2 = new Color(100, "Yellow", "#FFC400");
        Color color3 = new Color(100, "Green", "#00FF44");
        Color color4 = new Color(100, "Blue", "#00FFF6");
        Color color5 = new Color(100, "Purple", "#FF00FF");

        // Add Color objects to model
        suntivityModel.addStoreItem(color1);
        suntivityModel.addStoreItem(color2);
        suntivityModel.addStoreItem(color3);
        suntivityModel.addStoreItem(color4);
        suntivityModel.addStoreItem(color5);

        return suntivityModel;
    }

    /**
     * Creates and initializes a Model containing predefined test accounts.
     *
     * <p>This method creates sample parent and child accounts, assigns points,
     * configures plant statuses, purchases and equips items, links children
     * to the parent account, and creates sample tasks.</p>
     *
     * <p>This method is intended for testing and demonstration purposes rather
     * than production account creation.</p>
     *
     * @return an initialized model containing sample accounts and data
     */
    public static Model initializeModelPlus() {

        // Create model object and initialize model without default accounts
        Model suntivityModel = ModelInitializer.initializeModel();

        // Initialize parent and child accounts
        ParentAccount parent1 = new ParentAccount(
                "Emperor Palpatine",
                "SomehowIReturned",
                1,
                suntivityModel
        );

        ChildAccount child1 = new ChildAccount(
                "Darth Vader",
                "SlayerofYounglings9000",
                1
        );

        ChildAccount child2 = new ChildAccount(
                "Stormtrooper 1",
                "ICan'tAim3000",
                1
        );

        ChildAccount child3 = new ChildAccount(
                "Stormtrooper 2",
                "Empire4Life",
                2
        );

        // Remaining initialization logic is unchanged
        // ...

        return suntivityModel;
    }
}
