package data.manager;

import data.models.User;
import data.json.UserRepository;

import java.io.IOException;
import java.util.List;

public class UserManager {

    private final UserRepository repo = new UserRepository();
    private List<User> users;

    public UserManager() throws IOException {
        this.users = repo.loadAll();
    }

    public boolean checkLogin(String email, String password) {
        return users.stream()
                .anyMatch(u ->
                        u.getEmail().equals(email) &&
                                u.getPassword().equals(password)
                );
    }

    public boolean createNewUser(String name, String email, String password) throws IOException {
        users = repo.loadAll(); // refresh state

        if (users.stream().anyMatch(u -> u.getEmail().equals(email))) {
            return false;
        }

        users.add(new User(name, email, password));
        repo.saveAll(users);
        return true;
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) throws IOException {
        load();

        for (User u : users) {
            if (u.getEmail().equals(email)) {

                if (!u.getPassword().equals(oldPassword)) {
                    return false;
                }

                u.setPassword(newPassword);

                File file = new File(ServerConfig.USER_FILE);
                String json = mapper
                        .writerWithView(User.UserFileView.class)
                        .withDefaultPrettyPrinter()
                        .writeValueAsString(users);

                try (FileWriter fw = new FileWriter(file)) {
                    fw.write(json);
                    fw.flush();
                }

                return true;
            }
        }
        return false;
    }
}
