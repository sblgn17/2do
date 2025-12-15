package org;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kacper Bohaczyk
 * Die Klasse managed der Speicher und erstellen der Tasks, sowie das laden.
 */

public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();

    public TaskManager() {
        load();
    }


    public void addTask(String title, String description) throws IOException {
        tasks.add(new Task(title, description));
        save();
    }


    public void addTask(String title, String description, String date) throws IOException {
        tasks.add(new Task(title, description, date));
        save();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getTasksWithDate() {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.hasDate()) {
                result.add(t);
            }
        }
        return result;
    }

    public String getAllTasksString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            String date = "";
            if (t.getDate() != null) {
                date = t.getDate();
            }
              sb.append(t.getId()).append(";")
                    .append(t.getTitle()).append(";")
                    .append(t.getDescription()).append(";")
                    .append(date)
                    .append("\n");
        }
        return sb.toString();
    }


    private void load() {
        File file = new File("ToDO-Server/src/main/java/org/tasks.csv");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(";", -1);

                String title = p[1];
                String description = p[2];
                String date = null;

                if (p.length > 3) {
                    if (!p[3].isEmpty()) {
                        date = p[3];
                    }
                }


                if (date == null) {
                    tasks.add(new Task(title, description));
                } else {
                    tasks.add(new Task(title, description, date));
                }
            }
        } catch (IOException ignored) {}
    }

    private void save() throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("ToDO-Server/src/main/java/org/tasks.csv"))) {
            for (Task t : tasks) {

                String date = "";
                if (t.getDate() != null) {
                    date = t.getDate();
                }

                out.println(
                        t.getId() + ";" +
                                t.getTitle() + ";" +
                                t.getDescription() + ";" +
                                date
                );
            }
        }
    }


}
