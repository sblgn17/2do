package org;
import java.util.UUID;

/**
 * @author Kacper Bohaczyk
 * Objekt Task - Beinhaltet 2 Konstruktoren einer mit Datum einer ohne
 */
public class Task {

    private final String id;
    private final String title;
    private final String description;
    private final String date;

    public Task(String title, String description) {
        this.date = null;
        this.id = UUID.randomUUID().toString();
        this.title = title;
        if (description == null) {
            this.description = "";
        } else {
            this.description = description;
        }

    }

    public Task(String title, String description, String date) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.date = date;
        if (description == null) {
            this.description = "";
        } else {
            this.description = description;
        }

    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }

    public boolean hasDate() {
        return date != null;
    }
}
