package ui.controllers;

import client.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import ui.SceneManager;


import java.time.LocalDate;


public class DashboardController {

    @FXML private Label userAvatarLabel;
    @FXML private Label userNameLabel;
    @FXML private Label welcomeLabel;

    @FXML private Label openCountLabel;
    @FXML private Label inProgressCountLabel;
    @FXML private Label doneCountLabel;
    @FXML private Label upcomingEventsLabel;

    @FXML private Label tasksInfoLabel;

    @FXML
    public void initialize() {
        String email = Session.loggedInUserEmail != null
                ? Session.loggedInUserEmail
                : "admin@example.com";

        String avatar = "AD";
        if (email.length() >= 2) {
            avatar = email.substring(0, 2).toUpperCase();
        }

        userAvatarLabel.setText(avatar);
        userNameLabel.setText("Admin User");
        welcomeLabel.setText("Willkommen zurück, Admin User!");

        openCountLabel.setText("0");
        inProgressCountLabel.setText("0");
        doneCountLabel.setText("0");

        tasksInfoLabel.setText("Keine offenen Aufgaben");

        refreshDashboard();

    }
    @FXML
    private void onLogoutClick() {
        Stage stage = (Stage) userAvatarLabel.getScene().getWindow();
        SceneManager.switchTo(stage, "/ui/views/login.fxml");
    }
    @FXML
    private void openCalendar() {
        Stage stage = (Stage) userAvatarLabel.getScene().getWindow();
        SceneManager.switchTo(stage, "/ui/views/calendar.fxml");
    }
    @FXML
    public void onAddEventClick() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Neues Event");
            dialog.setHeaderText("Event hinzufügen");
            dialog.setContentText("Titel eingeben:");

            var result = dialog.showAndWait();
            if (result.isEmpty()) return;

            String title = result.get().trim();
            if (title.isBlank()) return;

            String date = LocalDate.now().toString();

            String cmd = "ADD_TASK " + Session.loggedInUserEmail + " " +date + " " + title;
            System.out.println("Sende an Server: '" + cmd + "'");

            String response = Session.client.send(cmd);
            System.out.println("Server Response: " + response);

            if (response.startsWith("OK")) {
                System.out.println("Event gespeichert!");
                refreshDashboard();
            } else {
                System.out.println("Fehler beim Speichern!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshDashboard() {
        try {

            String response = Session.client.send("GET_TASKCOUNT " + Session.loggedInUserEmail);
            int count = Integer.parseInt(response.trim());


            openCountLabel.setText(String.valueOf(count));
            inProgressCountLabel.setText("0");
            doneCountLabel.setText("0");

            if (count == 0) {
                tasksInfoLabel.setText("Keine offenen Aufgaben");
            } else {
                tasksInfoLabel.setText("Du hast " + count + " offene Aufgaben");
            }

        } catch (Exception e) {
            e.printStackTrace();

            tasksInfoLabel.setText("Fehler beim Laden der Aufgaben");
        }
    }



}
