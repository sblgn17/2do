package data.models;
import java.util.UUID;

/**
 * @author Kacper Bohaczyk
 * Objekt Task - Beinhaltet 2 Konstruktoren einer mit Datum einer ohne
 */
public class Task {

    private final String id;
    private final String date;
    private final String title;
    private final String description;
    private final boolean completed;
    private final boolean tbd;

    public Task() {
        this.id = null;
        this.date = null;
        this.title = null;
        this.description = null;
        this.completed = false;
        this.tbd = false;
    }

    public Task(String date, String title) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.title = title;
        this.description = "";
        this.completed = false;
        this.tbd = false;
    }

    public Task(String date, String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.tbd = false;
    }

    public String getId() { return id; }
    public String getDate() { return date; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String completed() { return description; }
    public String tbd() { return description; }
}
