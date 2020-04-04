package Contract;

import java.io.Serializable;

public class TaskList implements Serializable {

    private String availableTasks[];
    private String taskClassName[];

    public String[] getAvailableTasks() {
        return availableTasks;
    }

    public void setAvailableTasks(String[] availableTasks) {
        this.availableTasks = availableTasks;
    }

    public String[] getTaskClassName() {
        return taskClassName;
    }

    public void setTaskClassName(String[] taskClassName) {
        this.taskClassName = taskClassName;
    }
}
