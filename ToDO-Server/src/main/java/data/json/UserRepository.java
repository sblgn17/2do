package data.json;

import data.models.User;
import server.ServerConfig;

public class UserRepository extends JsonFileRepository<User> {

    public UserRepository() {
        super(ServerConfig.USER_FILE, User[].class);
    }
}
