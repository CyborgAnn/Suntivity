package edu.utsa.cs3443.suntivity.model;

import java.util.ArrayList;
import java.time.LocalDate;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a parent account in Suntivity.
 * A parent can manage children, create tasks, and approve completed tasks.
 */
public class ParentAccount extends Account {

    private ArrayList<ChildAccount> children;
    private ArrayList<Task> tasks;
    private final int linkingCode;
    private Model model;


    /**
     * Creates a parent account.
     *
     * @param userName parent's username
     * @param password parent's password
     * @param timeZone user's timezone
     * @param model    application model containing account data
     */
    public ParentAccount(String userName, String password, int timeZone, Model model) {

        super(userName, password, timeZone);

        children = new ArrayList<>();
        tasks = new ArrayList<>();

        this.model = model;

        linkingCode = generateLinkingCode(model);
        loadData(model);


    }


    /**
     * Generates a unique five-digit linking code.
     *
     * @param model application model used to verify unique codes
     * @return unique linking code
     */
    private int generateLinkingCode(Model model) {

        int code;

        do {

            code = (int) (Math.random() * 90000) + 10000;

        } while (model.getParentByLinkingCode(code) != null);


        return code;

    }


    /**
     * Returns parent's linking code.
     *
     * @return linking code
     */
    public int getLinkingCode() {

        return linkingCode;

    }


    /**
     * Adds a child account to this parent.
     *
     * @param child child account
     */
    public void addChild(ChildAccount child) {

        if (child != null && !children.contains(child)) {

            children.add(child);

            child.setParent(this);

            saveData();

        }

    }


    /**
     * Removes a child account.
     *
     * @param child child account
     */
    public void removeChild(ChildAccount child) {

        if (child != null) {

            child.setParent(null);

            children.remove(child);

        }

    }


    /**
     * Creates a task assigned to a child.
     *
     * @param child       child receiving the task
     * @param name        task name
     * @param description task details
     * @param dueDate      day task is due
     * @param dueTime     time task is due
     * @param points      task value
     * @param repeat      whether task repeats
     * @return newly created task
     */
    public Task createTask(
            ChildAccount child,
            String name,
            String description,
            LocalDate dueDate,
            int dueTime,
            int points,
            boolean repeat) {


        Task task =
                new Task(
                        child,
                        this,
                        name,
                        description,
                        dueDate,
                        dueTime,
                        points,
                        repeat
                );


        tasks.add(task);


        if (child != null) {

            child.addTask(task);

        }

        saveData();


        return task;

    }


    /**
     * Removes a task from the parent's list.
     *
     * @param task task to remove
     */
    public void removeTask(Task task) {

        tasks.remove(task);

    }


    /**
     * Approves or rejects a completed task.
     *
     * @param task     task being reviewed
     * @param approved approval status
     */
    public void answerPendingTask(Task task, boolean approved) {


        if (task == null) {

            return;

        }


        if (approved) {

            task.getChild().addPoints(task.getPoints());

            task.setStatus(TaskStatus.APPROVED);

            task.getChild().saveData();

        } else {

            task.setStatus(TaskStatus.DENIED);

        }

    }


    /**
     * Returns parent's tasks.
     *
     * @return task list
     */
    public ArrayList<Task> getTasks() {

        return tasks;

    }


    /**
     * Returns pending tasks waiting for approval.
     *
     * @return pending tasks
     */
    public ArrayList<Task> getPendingTasks() {


        ArrayList<Task> pending = new ArrayList<>();


        for (Task task : tasks) {

            if (task.getStatus() == TaskStatus.PENDING) {

                pending.add(task);

            }

        }


        return pending;

    }


    /**
     * Returns incomplete tasks.
     *
     * @return incomplete tasks
     */
    public ArrayList<Task> getIncompleteTasks() {


        ArrayList<Task> incomplete = new ArrayList<>();


        for (Task task : tasks) {

            if (task.getStatus() == TaskStatus.INCOMPLETE) {

                incomplete.add(task);

            }

        }


        return incomplete;

    }


    /**
     * Gets tasks assigned to a specific child.
     *
     * @param child child account
     * @return child's assigned tasks
     */
    public ArrayList<Task> getTasksForChild(ChildAccount child) {


        ArrayList<Task> result = new ArrayList<>();


        for (Task task : tasks) {

            if (task.getChild() == child) {

                result.add(task);

            }

        }


        return result;

    }


    /**
     * Returns parent's children.
     *
     * @return children list
     */
    public ArrayList<ChildAccount> getChildren() {

        return children;

    }


    /**
     * Returns application model.
     *
     * @return model
     */
    public Model getModel() {

        return model;

    }

    /**
     * Saves parent account information.
     */
    public void saveData() {

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
                            getUserName() + "_parentSave.txt"
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
                    "LinkingCode="
                            + linkingCode
            );


            // Save linked children
            for (ChildAccount child : children) {

                writer.println(
                        "Child="
                                + child.getUserName()
                );

            }


            // Save tasks
            for (Task task : tasks) {

                writer.println(
                        "Task="
                                + task.getName()
                                + ","
                                + task.getChild().getUserName()
                                + ","
                                + task.getDescription()
                                + ","
                                + task.getPoints()
                                + ","
                                + task.getStatus()
                                + ","
                                + task.getDueDate()
                                + ","
                                + task.getDueTime()
                                + ","
                                + task.isRepeat()
                );

            }


            writer.close();


            System.out.println(
                    "Parent data saved!"
            );


        } catch (IOException e) {

            e.printStackTrace();

        }
    }


        /**
         * Loads parent account information.
         */
        public void loadData(Model model){

            File file =
                    new File(
                            "src/main/resources/edu/utsa/cs3443/suntivity/DataFiles/"
                                    + getUserName()
                                    + "_parentSave.txt"
                    );


            if (!file.exists()) {

                System.out.println(
                        "No parent save found."
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


                    // Restore password
                    if(line.startsWith("Password=")){

                        String savedPassword =
                                line.substring(9);

                        System.out.println("Loaded password: " + savedPassword);

                        setPassword(savedPassword);

                    }



                    // Restore children
                    if(line.startsWith("Child=")){

                        String childName =
                                line.substring(6);


                        ChildAccount child =
                                model.getChildByUsername(childName);


                        if(child != null){

                            addChild(child);

                        }

                    }


                }


                reader.close();


                System.out.println(
                        "Parent data loaded!"
                );


            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }


