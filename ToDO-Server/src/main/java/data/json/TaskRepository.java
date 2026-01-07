package data.json;

import data.models.User;
import server.ServerConfig;

public class TaskRepository extends JsonFileRepository<User> {

    public TaskRepository() {
        super(ServerConfig.TASK_FILE, User[].class);
    }
}
