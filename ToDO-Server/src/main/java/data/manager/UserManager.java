package data.manager;

import data.models.User;
import java.io.*;
import java.util.*;

public class UserManager {

    private List<User> users = new ArrayList<>();

    public UserManager() {
        load();
    }

    public boolean checkLogin(String email, String password) {
        System.out.println("checkLogin(): email=" + email + ", pass=" + password);
        System.out.println(users.size() + " User loadet");
        return users.stream()
                .anyMatch(u -> u.getEmail().equals(email) && u.getPassword().equals(password));
    }

    public void load(){
        users.clear();

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("users.csv")){
            if(is == null){
                System.out.println("user.csv not found!");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(";");
                if (p.length < 3) continue;
                users.add(new User(p[0], p[1], p[2]));
            }
            System.out.println("load(): " + users.size() + " loadet User from file.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
