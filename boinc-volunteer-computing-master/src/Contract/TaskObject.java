package Contract;

import java.io.Serializable;

public class TaskObject implements Serializable {

    private Integer taskId = 0;
    private Integer credit = 0;
    private Task tObject = null;

    public TaskObject() {
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Task gettObject() {
        return tObject;
    }

    public void settObject(Task tObject) {
        this.tObject = tObject;
    }
}
