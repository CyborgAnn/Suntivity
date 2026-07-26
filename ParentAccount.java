package suntivity_model;

import java.util.ArrayList;

public class ParentAccount extends Account {

    private ArrayList<ChildAccount> children;
    private ArrayList<Task> tasks;
    private final int linkingCode;
    private Model model;

    public ParentAccount(String userName, String password, int timeZone, Model model) {
        super(userName, password, timeZone);
        children = new ArrayList<>();
        tasks = new ArrayList<>();
        linkingCode = generateLinkingCode(model);
        this.model = model;
    }

    private int generateLinkingCode(Model model){

        int code;

        do {
            code = (int)(Math.random() * 90000) + 10000;
        }
        while(model.getParentByLinkingCode(code) != null);

        return code;
    }

    public int getLinkingCode() {
        return linkingCode;
    }

    public void addChild(ChildAccount child) {
        children.add(child);
    }

    public void removeChild(ChildAccount child) {
        child.setParent(null);
        children.remove(child);
    }

    public Task createTask(ChildAccount child, String name,
                           String description, int month,
                           int day, int time, int points) {

        Task task = new Task(child, this, name, description,
                month, day, time, points);

        tasks.add(task);
        child.getTasks().add(task);

        return task;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void answerPendingTask(Task task, boolean approved) {
        if (approved) {
            task.getChild().addPoints(task.getPoints());
            task.setStatus(TaskStatus.COMPLETE);
        } else {
            task.setStatus(TaskStatus.INCOMPLETE);
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> getPendingTasks() {
        ArrayList<Task> pending = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.PENDING)
                pending.add(t);
        }

        return pending;
    }

    public ArrayList<Task> getIncompleteTasks() {
        ArrayList<Task> incomplete = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.INCOMPLETE)
                incomplete.add(t);
        }

        return incomplete;
    }

    public ArrayList<Task> getTasksForChild(ChildAccount child) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getChild() == child)
                result.add(t);
        }

        return result;
    }

    public ArrayList<ChildAccount> getChildren() {
        return children;
    }
}
