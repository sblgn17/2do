package data.models;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private List<Task> taskList;

    public void setEmail(String email) {
        this.email = email;
    }

    public User(){}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        taskList = new ArrayList<>();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}